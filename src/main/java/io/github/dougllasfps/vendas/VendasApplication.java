package io.github.dougllasfps.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VendasApplication {
	
//	@Bean
//	public CommandLineRunner commandLineRunner(@Autowired ClienteRepository clientes) {
//		return args -> {
//			Cliente c = new Cliente(null, "Fulano");
//			clientes.save(c);
//		};
//	}
	//usado para teste inicial
	//@Bean
	//public CommandLineRunner init(
			//@Autowired ClienteRepository clientes,
			//@Autowired PedidoRepository pedidos) {
		//return args -> {
			//clientes.save(new Cliente(null,"Dougllas"));
			//clientes.save(new Cliente(null,"Outro Cliente"));
			//Cliente fulano = new Cliente(null, "Fulano");
			//clientes.save(fulano);
			
			//Pedido p = new Pedido();
			//p.setCliente(fulano);
			//p.setDataPedido(LocalDate.now());
			//p.setTotal(BigDecimal.valueOf(100));
			
			//pedidos.save(p);
			
			//Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
			
			//System.out.println(cliente);
			//System.out.println(cliente.getPedidos());
			
			
			
			
			
			//List<Cliente> todosClientes = clientes.findAll();
			
			//todosClientes.forEach(System.out::println);
			
			//boolean existe = clientes.existsByNome("Dougllas");
			
			//String existente = existe ? "Sim" : "Não";
			
			//System.out.println("Existe um cliente com o nome Dougllas? "+ existente);
			
			//System.out.println("Atualizando clientes");
			
			/*todosClientes.forEach(c -> {
				c.setNome(c.getNome()+" atualizado.");
				clientes.save(c);
			});*/
			
			//todosClientes = clientes.findAll();
			//todosClientes.forEach(System.out::println);
			
			//System.out.println("Buscando clientes");
			//clientes.findByNomeLike("%gl%").forEach(System.out::println);
			
			//System.out.println("-=-=-=-=-=-=-=-=-=-");
			
			//System.out.println("Buscando clientes");
			//List<Cliente> result = clientes.encontrarPorNome("%gl%");
			//result.forEach(System.out::println);
			
			//System.out.println("usando sql nativo para encontrar nome");
			//List<Cliente> result1 = clientes.encontrarPorNomeNativo("%gl%");
			//result1.forEach(System.out::println);
			
			//System.out.println("Deletando com @Query");
			
			/*try {
				clientes.deletarPorNome("Dougllas");
				clientes.findAll().forEach(System.out::println);
			}catch (Exception e) {
				System.out.println("Deu errado: " + e.getMessage());
			}*/
			
			
			//System.out.println("-=-=-=-=-=-=-=-=-=-");
			
			/*System.out.println("deletando clientes");
			clientes.findAll().forEach(c -> {
				clientes.delete(c);
			});*/
			
			/*todosClientes = clientes.findAll();
			if(todosClientes.isEmpty()) {
				System.out.println("Nenhum cliente encontrado");
			}else {
				todosClientes.forEach(System.out::println);
			}*/
		//};
		
		
	//}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
