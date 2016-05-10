package cz.gopas.eshop;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import org.hsqldb.jdbc.*;
import org.springframework.context.annotation.*;
import cz.gopas.eshop.entity.*;
import cz.gopas.eshop.service.*;

@Configuration
@ComponentScan("cz.gopas.eshop.service")
public class HelloWorldSpringMain {

    // pridani beany do springoveho kontejneru
    // (nemuzu pouzit anotaci protoze nemam zdrojaky JDBCDataSource - org.hsqldb.jdbc.*)
    @Bean
    //@Bean(name = "ds")
    public DataSource dataSource(){
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setUser("sa");
        dataSource.setPassword("");
        dataSource.setURL("jdbc:hsqldb:hsql://localhost/eshop");
        return dataSource;
    }

    public static void main_TODO(String[] args) throws SQLException {

        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(HelloWorldSpringMain.class);

        List<Item> items = applicationContext.getBean(ItemService.class).getItems();
        System.out.println(items);

        DataSource dataSource = applicationContext.getBean(DataSource.class);
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("select * from item")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("name") + "=" + resultSet.getDouble("price"));
            }
        }

        applicationContext.close();

    }
}
