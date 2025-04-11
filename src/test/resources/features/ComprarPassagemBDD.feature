Feature: Comprar passagem
    Escolher e comprar passagem aereas

  Scenario: Comprar com sucesso
    Given que acesso o site "https://www.blazedemo.com/"
    When seleciono a origem "São Paolo" e destino "Cairo"
    And clico no botao Find Flights
    Then visualiza a lista de voos

  Scenario Outline: Comprar com sucesso
    Given que acesso o site "https://www.blazedemo.com/"
    When seleciono a <origem> e <destino>
    And clico no botao Find Flights
    Then visualiza a lista de voos

    Examples:
      | origem     | destino  |
      | "Portland" | "Dublin" |
      | "Paris"    | "Berlin" |
