package com.maskun.moard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

@PropertySource("classpath:/com/maskun/moard/application.properties")
@SpringBootTest
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Value("${spring.datasource.url}")
    String myUrl;

    @Value("${spring.datasource.username}")
    String myUserName;

    @Value("${spring.datasource.password}")
    String myPassWord;

    @Test
    public void DataSource빈이_자동으로_잘구성되었는지_테스트() {
        // dataSource가 null이 아닌지 확인
        assertNotNull(dataSource);

        // JdbcTemplate이 null이 아닌지 확인
        assertNotNull(jdbcTemplate);

        //프로퍼티 내용과 autoconfig된 datasource가 가진 내용이 일치하는지 확인하기
        assertThat(dataSourceProperties.getUrl()).isEqualTo(myUrl);
        assertThat(dataSourceProperties.getUsername()).isEqualTo(myUserName);
        assertThat(dataSourceProperties.getPassword()).isEqualTo(myPassWord);

        //실제 DB에서 쿼리로 사용자정보를 받아서 출력해보기
         List<HashMap<String,String>> list = jdbcTemplate.query("SELECT USER() as 'user', DATABASE() as 'database'", (rs, rowNum) -> {
            HashMap<String,String>  map = new HashMap<>();
            map.put("user",rs.getString("user"));
            map.put("database",rs.getString("database"));
            return map;
        });

         String dataSourceUser = list.get(0).get("user");
         String dataSourceDateBase = list.get(0).get("database");

        System.out.println("[testlog] db사용자:"+ dataSourceUser +"\t 데이터베이스:"+dataSourceDateBase);

    }
}