package com.codekata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CheckOutTest {

	public int preco(String produtos) {
		CheckOut co = new CheckOut("prices/prices.txt");
		for (String s : produtos.split("")) {
			co.scan(s);
		}
		return co.total();
	}

	@Test
	public void testando() {
		assertEquals(0, preco(""));
		assertEquals(50, preco("A"));
		assertEquals(80, preco("AB"));
		assertEquals(115, preco("CDBA"));

		assertEquals(100, preco("AA"));
		assertEquals(130, preco("AAA"));
		assertEquals(180, preco("AAAA"));
		assertEquals(230, preco("AAAAA"));
		assertEquals(260, preco("AAAAAA"));

		assertEquals(160, preco("AAAB"));
		assertEquals(175, preco("AAABB"));
		assertEquals(190, preco("AAABBD"));
		assertEquals(190, preco("DABABA"));
	}
	
	@Test
	public void testIncremental() {
		CheckOut co = new CheckOut("prices/prices.txt");
		assertEquals(0, co.total());
		
		co.scan("A");
		assertEquals(50, co.total());
		co.scan("B");
		assertEquals(80, co.total());
		co.scan("A");
		assertEquals(130, co.total());
		co.scan("A");
		assertEquals(160, co.total());
		co.scan("B");
		assertEquals(175, co.total());
	}

}
