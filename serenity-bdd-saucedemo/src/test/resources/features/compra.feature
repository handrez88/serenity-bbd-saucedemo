Feature: Prueba carrito de compras

  @saucedemo
  Scenario: Iniciar sesión y realizar una compra
    Given que estoy en la página de inicio de sesión
    When ingreso mis credenciales válidas
    And hago clic en login
    Then debería ser redirigido a la página de inventario

    # Probar compra
    When añado el producto #1 y #3 al carrito
    And reviso el total del carrito
    And hago clic en checkout
    And lleno el formulario con los siguientes datos:
      | Nombre     | Apellido  | Código Postal |
      | Juan       | Pérez     | 12345         |
    And confirmo la compra
    Then debería salir el mensaje "Thank you for your order"
