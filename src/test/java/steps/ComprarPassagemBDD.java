package steps;

import io.cucumber.java.en.Given; // Importa a anotação para etapas Given do Cucumber
import io.cucumber.java.en.Then;  // Importa a anotação para etapas Then do Cucumber
import io.cucumber.java.en.When;  // Importa a anotação para etapas When do Cucumber

public class ComprarPassagemBDD {

    @Given("que acesso o site {string}") // Passo que acessa a URL fornecida no .feature
    public void que_acesso_o_site(String string) {
        
    }

    @When("seleciono a origem {string} e destino {string}") // Passo para selecionar cidades de origem e destino
    public void seleciono_a_origem_e_destino(String string, String string2) {
        
    }

    @When("clico no botao Find Flights") // Passo para clicar no botão de busca de voos
    public void clico_no_botao_find_flights() {
        
    }

    @Then("visualiza a lista de voos") // Passo que valida se a lista de voos foi exibida
    public void visualiza_a_lista_de_voos() {
        
    }
}

