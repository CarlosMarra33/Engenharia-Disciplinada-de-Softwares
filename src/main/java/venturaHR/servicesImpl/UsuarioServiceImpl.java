package venturaHR.servicesImpl;

import venturaHR.models.Empresa;
import venturaHR.models.Usuario;
import venturaHR.modelsDTO.UsuarioRequestDTO;
import venturaHR.services.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{

    @Override
    public void criacaoDeConta(UsuarioRequestDTO usuarioDto) {
        //criar uma lista de coisas
        if (usuarioDto.getCpf() == null && usuarioDto.getCnpj()!= null) {
            Empresa empresa = new Empresa(usuarioDto.getNome(), usuarioDto.getEmail(), usuarioDto.getPassword(), usuarioDto.getCnpj());

        }
    }
    
}
