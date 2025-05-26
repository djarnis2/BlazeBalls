package dk.sdu.cbse.main;

import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.ServiceLoader;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;



@Configuration
public class SpringConfiguration {

    public SpringConfiguration() {
    }

    @Bean
    public Game game() {
        return new Game(getPluginServices(),getEntityProcessingServices(),getPostEntityProcessingServices());
    }


    public List<IGamePluginService> getPluginServices() {

        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


    public List<IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }


    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
