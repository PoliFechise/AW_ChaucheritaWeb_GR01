package serviciosRest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import model.dao.CategoriaDAO;
import orm.entities.Categoria;

@Path("/ingreso")
public class ConsultarCategoriasIngreso {

    private CategoriaDAO categoriaDAO;

    public ConsultarCategoriasIngreso() {
        this.categoriaDAO = new CategoriaDAO();
    }

    @Path("/consultar-categorias")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar() {
        List<Categoria> categorias = categoriaDAO.obtenerCategoriasIngreso();
        
        return Response.ok(categorias)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .build();
    }
}
