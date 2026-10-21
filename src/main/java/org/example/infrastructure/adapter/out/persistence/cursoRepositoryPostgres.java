package org.example.infrastructure.adapter.out.persistence;

import org.example.domain.model.curso;
import org.example.domain.repository.cursoRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class cursoRepositoryPostgres implements cursoRepository {

    private final String url;
    private final String usuario;
    private final String password;

    public cursoRepositoryPostgres() {
        this(
                System.getenv().getOrDefault(
                        "DB_URL",
                        "jdbc:postgresql://localhost:5432/sistema_gestion_academica"
                ),
                System.getenv().getOrDefault("DB_USER", "postgres"),
                System.getenv().get("DB_PASSWORD")
        );
    }

    public cursoRepositoryPostgres(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }

    private Connection conectar() throws SQLException {
        if (password == null || password.isBlank()) {
            throw new IllegalStateException(
                    "Configura la variable de entorno DB_PASSWORD."
            );
        }
        return DriverManager.getConnection(url, usuario, password);
    }

    @Override
    public void guardar(curso curso) {
        String sql = """
            INSERT INTO cursos (id, nombre)
            VALUES (?, ?)
            """;

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, curso.getId());
            ps.setString(2, curso.getNombre());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "No se pudo registrar el curso.", e
            );
        }
    }

    @Override
    public List<curso> listar() {
        String sql = """
            SELECT id, nombre
            FROM cursos
            ORDER BY id
            """;

        List<curso> cursos = new ArrayList<>();

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                cursos.add(new curso(
                        rs.getInt("id"),
                        rs.getString("nombre")
                ));
            }
            return cursos;

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "No se pudieron listar los cursos.", e
            );
        }
    }

    @Override
    public void guardar(List<curso> cursos) {
        // Compatibilidad con operaciones por lote si fuese necesario
    }

    @Override
    public Optional<curso> buscarPorId(int id) {
        String sql = """
            SELECT id, nombre
            FROM cursos
            WHERE id = ?
            """;

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new curso(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    ));
                }
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "No se pudo buscar el curso.", e
            );
        }
    }

    @Override
    public boolean actualizar(curso curso) {
        String sql = """
            UPDATE cursos
            SET nombre = ?
            WHERE id = ?
            """;

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, curso.getNombre());
            ps.setInt(2, curso.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "No se pudo actualizar el curso.", e
            );
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM cursos WHERE id = ?";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "No se pudo eliminar el curso.", e
            );
        }
    }
}