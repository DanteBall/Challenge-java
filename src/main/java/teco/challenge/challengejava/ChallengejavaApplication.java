package teco.challenge.challengejava;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import teco.challenge.challengejava.dominio.Camino;
import teco.challenge.challengejava.dominio.PuntoDeVenta;
import teco.challenge.challengejava.dto.DTOCamino;
import teco.challenge.challengejava.repositorios.RepoPuntoVenta;
import teco.challenge.challengejava.servicios.ServicioCamino;
import teco.challenge.challengejava.servicios.ServicioPuntoVenta;

import java.math.BigDecimal;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class ChallengejavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengejavaApplication.class, args);

	}

	//Configuracion inicial de la base de datos y carga de cache
	@Bean
	public CommandLineRunner loadData(ServicioPuntoVenta servicioPuntoVenta, ServicioCamino servicioCamino) {

		return args2 -> {
			//Configuracion Puntos de venta
			PuntoDeVenta punto1 = new PuntoDeVenta();
			punto1.setNombre("CABA");

			PuntoDeVenta punto2 = new PuntoDeVenta();
			punto2.setNombre("GBA_1");

			PuntoDeVenta punto3 = new PuntoDeVenta();
			punto3.setNombre("GBA_2");

			PuntoDeVenta punto4 = new PuntoDeVenta();
			punto4.setNombre("Santa Fe");

			PuntoDeVenta punto5 = new PuntoDeVenta();
			punto5.setNombre("Cordoba");

			PuntoDeVenta punto6 = new PuntoDeVenta();
			punto6.setNombre("Misiones");

			PuntoDeVenta punto7 = new PuntoDeVenta();
			punto7.setNombre("Salta");

			PuntoDeVenta punto8 = new PuntoDeVenta();
			punto8.setNombre("Chubut");

			PuntoDeVenta punto9 = new PuntoDeVenta();
			punto9.setNombre("Santa Cruz");

			PuntoDeVenta punto10 = new PuntoDeVenta();
			punto10.setNombre("Catamarca");

			servicioPuntoVenta.savePuntoVenta(punto1);
			servicioPuntoVenta.savePuntoVenta(punto2);
			servicioPuntoVenta.savePuntoVenta(punto3);
			servicioPuntoVenta.savePuntoVenta(punto4);
			servicioPuntoVenta.savePuntoVenta(punto5);
			servicioPuntoVenta.savePuntoVenta(punto6);
			servicioPuntoVenta.savePuntoVenta(punto7);
			servicioPuntoVenta.savePuntoVenta(punto8);
			servicioPuntoVenta.savePuntoVenta(punto9);
			servicioPuntoVenta.savePuntoVenta(punto10);
			servicioPuntoVenta.getPuntosVenta();

			//Configuracion caminos
			DTOCamino camino1 = new DTOCamino(punto1.getId(),punto2.getId(), BigDecimal.valueOf(2));
			DTOCamino camino2 = new DTOCamino(punto1.getId(),punto3.getId(), BigDecimal.valueOf(3));
			DTOCamino camino3 = new DTOCamino(punto2.getId(),punto3.getId(), BigDecimal.valueOf(5));
			DTOCamino camino4 = new DTOCamino(punto2.getId(),punto4.getId(), BigDecimal.valueOf(10));
			DTOCamino camino5 = new DTOCamino(punto1.getId(),punto4.getId(), BigDecimal.valueOf(11));
			DTOCamino camino6 = new DTOCamino(punto4.getId(),punto5.getId(), BigDecimal.valueOf(5));
			DTOCamino camino7 = new DTOCamino(punto2.getId(),punto5.getId(), BigDecimal.valueOf(14));
			DTOCamino camino8 = new DTOCamino(punto6.getId(),punto7.getId(), BigDecimal.valueOf(32));
			DTOCamino camino9 = new DTOCamino(punto8.getId(),punto9.getId(), BigDecimal.valueOf(11));
			DTOCamino camino10 = new DTOCamino(punto10.getId(),punto7.getId(), BigDecimal.valueOf(5));
			DTOCamino camino11 = new DTOCamino(punto3.getId(),punto8.getId(), BigDecimal.valueOf(10));
			DTOCamino camino12 = new DTOCamino(punto5.getId(),punto8.getId(), BigDecimal.valueOf(30));
			DTOCamino camino13 = new DTOCamino(punto10.getId(),punto5.getId(), BigDecimal.valueOf(5));
			DTOCamino camino14 = new DTOCamino(punto4.getId(),punto6.getId(), BigDecimal.valueOf(6));

			servicioCamino.saveCamino(camino1);
			servicioCamino.saveCamino(camino2);
			servicioCamino.saveCamino(camino3);
			servicioCamino.saveCamino(camino4);
			servicioCamino.saveCamino(camino5);
			servicioCamino.saveCamino(camino6);
			servicioCamino.saveCamino(camino7);
			servicioCamino.saveCamino(camino8);
			servicioCamino.saveCamino(camino9);
			servicioCamino.saveCamino(camino10);
			servicioCamino.saveCamino(camino11);
			servicioCamino.saveCamino(camino12);
			servicioCamino.saveCamino(camino13);
			servicioCamino.saveCamino(camino14);
			servicioCamino.getCaminos();

		};
	}

}
