import com.test.mapper.PermissionMapper;
import com.test.model.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author herixin
 * @create 2022-12-15 12:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class test {
    @Autowired
    PermissionMapper permissionMapper;

    @Test
    public void test() {
        List<Permission> permissions = permissionMapper.selectMenu(null);
        System.out.println("permissions = " + permissions);

    }
}
