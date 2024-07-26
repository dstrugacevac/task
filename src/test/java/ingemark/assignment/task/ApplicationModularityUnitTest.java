package ingemark.assignment.task;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;


class ApplicationModularityUnitTest {

	ApplicationModules modules = ApplicationModules.of(TaskApplication.class);

	@Test
	void verifiesModularStructure() {
		modules.verify();
	}

	@Test
	void createModuleDocumentation() {
		new Documenter(modules)
				.writeDocumentation()
				.writeIndividualModulesAsPlantUml();
	}

	@Test
	void createApplicationModuleModel() {
		modules.forEach(System.out::println);
	}

	@Test
	void renderDocumentation() {
		var canvasOptions = Documenter.CanvasOptions.defaults();
		var diagramOptions = Documenter.DiagramOptions.defaults().withStyle(Documenter.DiagramOptions.DiagramStyle.UML);
		new Documenter(modules)
				.writeDocumentation(diagramOptions, canvasOptions);
	}
}
