package org.example.infrastructure.adapter.out.persistence;
import org.example.domain.repository.estudianteRepository;
import org.example.domain.model.estudiante;
import org.example.domain.repository.estudianteRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class estudianteRepositoryPostgres implements estudianteRepository {

    private final String url;
    private final String usuario;
    private final String password;

    public estudianteRepositoryPostgres() {
        this(
                System.getenv().getOrDefault(
                        "DB_URL",
                        "jdbc:postgresql://localhost:5432/sistema_gestion_academica"
                ),
                System.getenv().getOrDefault("DB_USER", "postgres"),
                System.getenv().get("DB_PASSWORD")
        );
    }

    // Constructor adicional para configurar o probar la conexión.
    public estudianteRepositoryPostgres(
            String url, String usuario, String password) {
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

        return DriverManager.getConnection(
                url, usuario, password
        );
    }

    @Override
    public void guardar(estudiante estudiante) {
        String sql = """
            INSERT INTO estudiantes (id, nombre, correo)
            VALUES (?, ?, ?)
            """;

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, estudiante.getId());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getCorreo());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "No se pudo registrar al estudiante.", e
            );
        }
    }

    @Override
    public List<estudiante> listar() {
        String sql = """
            SELECT id, nombre, correo
            FROM estudiantes
            ORDER BY id
            """;

        List<estudiante> estudiantes = new ArrayList<>();

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                estudiantes.add(new estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo")
                ));
            }

            return estudiantes;

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "No se pudieron listar los estudiantes.", e
            );
        }
    }

    @Override
    public void guardar(List<estudiante> estudiantes) {

    }

    @Override
    public Optional<estudiante> buscarPorId(int id) {
        String sql = """
            SELECT id, nombre, correo
            FROM estudiantes
            WHERE id = ?
            """;

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new estudiante(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("correo")
                    ));
                }

                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "No se pudo buscar al estudiante.", e
            );
        }
    }

    @Override
    public boolean actualizar(estudiante estudiante) {
        String sql = """
            UPDATE estudiantes
            SET nombre = ?, correo = ?
            WHERE id = ?
            """;

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getCorreo());
            ps.setInt(3, estudiante.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "No se pudo actualizar al estudiante.", e
            );
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM estudiantes WHERE id = ?";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "No se pudo eliminar al estudiante.", e
            );
        }
    }
}
