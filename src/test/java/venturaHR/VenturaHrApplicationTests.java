package venturaHR;

import com.venturaHR.domain.entity.Vaga;
import com.venturaHR.service.VagaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertTrue;

class VenturaHrApplicationTests {

	@Autowired
	private VagaService vagaService;

	@Test
	void criarVaga() throws Exception {
		List<Vaga> vagas = vagaService.pegarTodas();
		assertTrue(vagas.size() != 0);
	}

}
