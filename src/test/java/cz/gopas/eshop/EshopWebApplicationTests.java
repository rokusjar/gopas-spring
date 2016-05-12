package cz.gopas.eshop;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;
import org.springframework.web.method.annotation.*;
import cz.gopas.eshop.exception.*;
import cz.gopas.eshop.service.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EshopWebApplication.class)
@WebAppConfiguration
public class EshopWebApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	@Test
	public void testGetJsonItemDetail() throws Exception {
		mockMvc.perform(get("/item/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Java in 21 days"));
	}

	@Autowired
	private ItemService itemService;

	@Test
	public void testGetItemSucces()throws Exception{
		assertNotNull(itemService.getItem(1));
	}

	@Test(expected = NumberFormatException.class)
	public void testGetItemIdNotInteger() throws Exception{
		assertNotNull(itemService.getItem(Integer.parseInt("a")));

	}

	@Test(expected = ItemNotFoundException.class)
	public void testGetItemInvalidId() throws Exception{
		itemService.getItem(-1);

	}

}
