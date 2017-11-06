Prácticas de diseño y desarrollo
==========

- La clase *"App"* gestiona la información del dominio: el stock de bicicletas, los alquileres y las promociones. Desde aquí se invoca al método para realizar un alquiler. 

- Para el dominio plateado se identificaron tres tipos de alquileres (por hora, día y semana) el diseño se realizó con herencia simple mediante la clase abstracta *"RentalType"* y las concretas *"PerHourRentalType"*, *"PerDayRentalType"* y *"PerWeekRentalType"*. La propiedad *"pricePerBike"* tiene el monto a cobrar por bicicleta.

- La información de los alquileres que se van realizando se almacena en *“Rental"*, cuyas propiedades son:
  - clientId: Almacena alguna identificación del cliente.
  - date: Mantiene la fecha en la que se realiza el alquiler.
  - bikes: Instancias de las bicicletas que alquila el cliente. Éstas se obtienen al hacer el control de stock en el método *"rent()"*.
  - type: Refiere a la clase concreta que representa el tipo de alquiler (por hora, día o semana).
  - timeUnit: Indica las unidades de tiempo que dura el alquiler.
  - totalPrice: Mantiene el precio que debe pagar el cliente por el alquiler.

- La clase *"Bike"* representa una bicicleta con su nombre y descripción. Se podría haber manejado como un simple entero pero me pareció una entidad importante del dominio con lo cual amerita mantenerlo como objeto independiente.

- En cuanto a promociones, el diseño se pensó para poder tener varías. Se creó la interface *"Promotion"* con su método *"apply"*. Dicho método tendrá la lógica para aplicar la promoción verificando si el alquiler (que se recibe como parámetro) aplica a la misma. En el caso de que un alquiler aplique a más de una promoción, el precio final es el de menor monto. Esta lógica esta implementada en el método *"defineTotalPrice()"* de la clase *"App"*. La clase *"FamilyPromotion"* implementa la interface *"Promotion"* y se creó porque el dominio de alquiler de bicicletas la usa explícitamente.

Cómo correr los test
==========

Ejecutando el comando `mvn verify` se ejecutan los test de la aplicación.

Se ha instalado el plugin **_jacoco_** para verificar la cobertura. Al finalizar la ejecución de los tests el reporte puede visualizarse en el archivo "index.html" 
ubicado en la carpeta `target/site/jacoco` relativa a la raíz del proyecto.

Para garantizar que la cobertura es mayor al 85% se utiliza el goal `check` de jacoco que falla el build de no cumplirse la condición.
