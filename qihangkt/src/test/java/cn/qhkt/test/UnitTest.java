package cn.qhkt.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mansys.dao.ResourceDao;
import cn.mansys.dao.UserDao;
import cn.mansys.model.Resource;
import cn.mansys.service.ResourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UnitTest {

	@Autowired
	UserDao userDao;
	@Autowired
	ResourceDao resourceDao;
	@Autowired
	ResourceService resourceService;

	@Test
	public void test() {
		List<Resource> resources=new ArrayList<>();
		resources.add(new Resource("/tssx",3));
		resourceService.initResource(resources);
	}

}
