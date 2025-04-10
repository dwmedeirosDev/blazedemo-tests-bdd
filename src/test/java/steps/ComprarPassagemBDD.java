package steps; // Define o pacote onde essa classe está localizada (organiza o código dentro do projeto)

import static org.junit.Assert.assertEquals; // Importa o método assertEquals para fazer comparações em testes

import java.time.Duration; // Classe usada para definir durações de tempo (ex: esperas implícitas e explícitas)

import org.openqa.selenium.By; // Classe usada para localizar elementos na página (por id, name, class, xpath, etc.)
import org.openqa.selenium.WebDriver; // Interface principal para controle do navegador
import org.openqa.selenium.WebElement; // Representa um elemento da página (botão, campo, etc.)
import org.openqa.selenium.chrome.ChromeDriver; // Classe que representa o navegador Google Chrome

import io.cucumber.java.Before; // Importa a anotação para etapas Before do Cucumber
import io.cucumber.java.After; // Importa a anotação para etapas After do Cucumber
import io.cucumber.java.en.Given; // Importa a anotação para etapas Given do Cucumber
import io.cucumber.java.en.When; // Importa a anotação para etapas When do Cucumber
import io.cucumber.java.en.And; // Importa a anotação para etapas And do Cucumber
import io.cucumber.java.en.Then; // Importa a anotação para etapas Then do Cucumber

import io.github.bonigarcia.wdm.WebDriverManager; // Importa o gerenciador automático de drivers dos navegadores

public class ComprarPassagemBDD {

    WebDriver driver; // Instância do WebDriver para controlar o navegador
    String origem;  // Variável para armazenar o valor origem (vinda do .feature)
    String destino; // Variável para armazenar o valor destino (vinda do .feature)

    @Before // Executa antes de cada cenário de teste
    public void iniciar() {
        WebDriverManager.chromedriver().setup(); // Faz o download e configura automaticamente o ChromeDriver
        driver = new ChromeDriver(); // Instancia e inicializa o navegador Chrome
        // Espera implícita de até 5 segundos para encontrar elementos
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().window().maximize(); // Maximiza a janela do navegador ao iniciar o teste
    }

    @After // Executa após cada cenário de teste
    public void finalizar() {
        driver.quit(); // Encerra a sessão do navegador e fecha todas as janelas abertas
    }

    @Given("que acesso o site {string}") // Passo que acessa a URL fornecida no .feature
    public void que_acesso_o_site(String url) {
        driver.get(url); // Abre o navegador e navega para a URL especificada
    }

    @When("seleciono a origem {string} e destino {string}") // Passo para selecionar cidades de origem e destino
    public void seleciono_a_origem_e_destino(String origem, String destino) {
        {
            this.origem = origem; // Armazena o valor recebido parâmetro na variável da classe para uso em outros métodos
            WebElement combo = driver.findElement(By.name("fromPort")); // Localiza o combo de origem pelo atributo name
            combo.click(); // Clica para abrir a lista de opções
            // Seleciona a opção de origem com base no texto
            combo.findElement(By.xpath("//option[.='" + origem + "']")).click();
        }
        {
            this.destino = destino; // Armazena o valor recebido parâmetro na variável da classe para uso em outros métodos
            WebElement combo = driver.findElement(By.name("toPort")); // Localiza o combo de destino pelo atributo name
            combo.click(); // Clica para abrir a lista de opções
            // Seleciona a opção de destino com base no texto
            combo.findElement(By.xpath("//option[.='" + destino + "']")).click();
        }

    }

    @And("clico no botao Find Flights") // Passo para clicar no botão de busca de voos
    public void clico_no_botao_find_flights() {
        driver.findElement(By.cssSelector(".btn-primary")).click(); // Clica no botão "Find Flights"
    }

    @Then("visualiza a lista de voos") // Passo que valida se a lista de voos foi exibida
    public void visualiza_a_lista_de_voos() {
        // Valida se o título exibido na página corresponde ao texto esperado com a origem e o destino selecionados
        assertEquals("Flights from " + origem + " to " + destino + ":", driver.findElement(By.cssSelector("h3")).getText());
    }
}