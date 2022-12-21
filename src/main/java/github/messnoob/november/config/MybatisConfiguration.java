package github.messnoob.november.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"github.messnoob.november.model.mapper"}, sqlSessionTemplateRef = "fsqlSessionTemplate")
public class MybatisConfiguration {

    @Value("${spring.datasource.driverClassName}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.initSize}")
    private int initSize;
    @Value("${spring.datasource.minSize}")
    private int minSize;
    @Value("${spring.datasource.maxSize}")
    private int maxSize;
    @Value("${spring.mapper.path}")
    private String mainMapperPath;
    @Value("${spring.mapper.acquireRetryAttempts}")
    private int mainAcquireRetryAttempts;
    @Value("${spring.mapper.acquireRetryDelay}")
    private int mainAcquireRetryDelay;
    @Value("${spring.mapper.breakAfterAcquireFailure}")
    private boolean breakAfterAcquireFailure;
    @Value("${spring.mapper.testConnectionOnCheckin}")
    private boolean testConnectionOnCheckin;
    @Value("${spring.mapper.idleConnectionTestPeriod}")
    private int idleConnectionTestPeriod;
    @Value("${spring.mapper.preferredTestQuery}")
    private String preferredTestQuery;

    @Primary
    @Bean(name = "fdataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource fdataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setAutoCommitOnClose(false);
        dataSource.setInitialPoolSize(initSize);
        dataSource.setMinPoolSize(minSize);
        dataSource.setMaxPoolSize(maxSize);
        dataSource.setAcquireRetryAttempts(mainAcquireRetryAttempts);
        dataSource.setAcquireRetryDelay(mainAcquireRetryDelay);
        dataSource.setBreakAfterAcquireFailure(breakAfterAcquireFailure);
        dataSource.setTestConnectionOnCheckin(testConnectionOnCheckin);
        dataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
        if(preferredTestQuery!=null&&preferredTestQuery.trim().length()>0) {
            dataSource.setPreferredTestQuery(preferredTestQuery);
        }
        return dataSource;
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() throws Exception {
        return new DataSourceTransactionManager(fdataSource());
    }

    @Primary
    @Bean
    public SqlSessionFactory fsqlSessionFactory(@Qualifier("fdataSource") DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            bean.setMapperLocations(resolver.getResources(mainMapperPath));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Primary
    @Bean
    public SqlSessionTemplate fsqlSessionTemplate(@Qualifier("fsqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }
}
