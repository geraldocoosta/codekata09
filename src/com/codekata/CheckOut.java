package com.codekata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CheckOut {
	private final List<Product> produtos;
	private final Map<Product, Integer> qtdProdutos;
	private final static String REGEX_MATCHES_PRICES_PROMO = "^[\\s]+[\\w]+[\\s]+[\\d]+[\\s]+[\\d]+[\\s]+[\\d]+";
	private final static String REGEX_MATCHES_PRICES = "^[\\s]+[\\w]+[\\s]+[\\d]+";

	public CheckOut(String pathPrices) {
		produtos = new ArrayList<>();
		qtdProdutos = new HashMap<>();
		popularProdutos(pathPrices);
		popularMapa();
	}

	private void popularMapa() {
		produtos.stream().forEach(p -> qtdProdutos.put(p, 0));
	}

	private void popularProdutos(String pathPrices) {
		try {
			Files.readAllLines(Paths.get(pathPrices)).stream()
					.map(s -> s.replaceAll("for", " "))
					.map(String::toLowerCase)
					.filter(s -> s.matches(REGEX_MATCHES_PRICES) || s.matches(REGEX_MATCHES_PRICES_PROMO))
					.map(s -> s.substring(4))
					.map(s -> s.split("[\\s]+"))
					.forEach(s -> {
						if (s.length > 2) {
							produtos.add(new Product(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]),
									Integer.parseInt(s[3])));
						} else {
							produtos.add(new Product(s[0], Integer.parseInt(s[1])));
						}
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void scan(String s) {
		produtos.stream().forEach(p -> {
			if (s.equalsIgnoreCase(p.getNome())) {
				qtdProdutos.put(p, qtdProdutos.get(p) + 1);
			}
		});
	}

	public int total() {
		return qtdProdutos.entrySet().stream().mapToInt(entry -> entry.getKey().getPrecoItens(entry.getValue())).sum();
	}
}
