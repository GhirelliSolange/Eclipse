package Tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Metodos.Metodos;

public class PetShop2 extends Metodos {

	private WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void before() {
		// Configuro el driver
		driver = setup();

	}

	@AfterMethod(alwaysRun = true)
	public void after() { // Cierro el Navegador
		driver.quit();

	}

	@Test
	public void C01_Google() throws IOException {

		String nombre = null;
		String animal = null;
		String producto = null;
		String dato = null;
		Connection con = null;

		// Abro navegador
		driver.navigate().to("https://petstore.octoperf.com/actions/Catalog.action");
		File file = new File("C:\\Users\\HD345AP\\Downloads\\StockconPrecio");
		try (FileWriter fw = new FileWriter(file, true)) {
			String lineSeparator = System.getProperty("line.separator");

			fw.write("----'Productos que tienen stock'----");
			fw.write(lineSeparator);

			try {
				String url =
						"jdbc:sqlite:C:\\Users\\HD345AP\\Downloads\\sqlite-tools-win32-x86-3400000\\sqlite-tools-win32-x86-3400000\\product.db";
				con = DriverManager.getConnection(url);

				String sql_select = "SELECT * FROM Stock";

				Statement s = con.createStatement();
				Statement s2 = con.createStatement();
				ResultSet rs = s.executeQuery(sql_select);
				
				con.setAutoCommit(true);
				
				while(rs.next()) {
					
					nombre = rs.getString("Nombre");
					animal = rs.getString("Animal");
					producto = rs.getString("Producto");
					dato = nombre + " " + animal + " " + producto;

					System.out.println("El producto es: " + dato);
					
					switch (nombre){
			        case "Dog":{
			
			        	driver.findElement(By.cssSelector("a:nth-of-type(2) > img")).click();

						if (animal.equals("Poodle")) {
							driver.findElement(By.linkText(producto)).click();

							driver.findElement(By.xpath("(//a[contains(text(),'Add to Cart')])[1]")).click();
							System.out.println("Se añadio al carrito el producto: " + nombre + " " + animal);

							String stock1 = driver.findElement(By.cssSelector("tr:nth-of-type(3) > td:nth-of-type(4)")).getText();

							if (stock1.contains("true")) {
								System.out.println("El producto tiene stock.");
								s2.executeUpdate("UPDATE Stock SET Stock = 'Tiene Stock' WHERE rowid = 2");
								System.out.println("------------------");

								String precio = driver.findElement(By.cssSelector("tr:nth-of-type(3) > td:nth-of-type(6)")).getText();

								fw.write(nombre + " " + animal + " tiene stock. Su precio es de " + precio + ".");
								fw.write(lineSeparator);

							} else {
								System.out.println("El producto " + animal + "no tiene stock.");
								s2.executeUpdate("UPDATE Stock SET Stock = 'No Tiene Stock' WHERE rowid = 2");
								System.out.println("------------------");

								driver.findElement(By.xpath("//a[@href='/actions/Cart.action?removeItemFromCart=&workingItemId=EST-8']"))
									.click();
								System.out.println("Se elimino del carrito.");

							}
						}
						
			            break;
			
			        }
			
			        case "Birds":{
			
			        	driver.findElement(By.cssSelector("a:nth-of-type(5) > img")).click();

						if (animal.contains("Finch")) {
							driver.findElement(By.linkText(producto)).click();

							driver.findElement(By.xpath("(//a[contains(text(),'Add to Cart')])[1]")).click();
							System.out.println("Se añadio al carrito el producto: " + nombre + " " + animal);

							String stock1 = driver.findElement(By.cssSelector("tr:nth-of-type(2) > td:nth-of-type(4)")).getText();

							if (stock1.contains("true")) {
								System.out.println("El producto tiene stock.");
								s2.executeUpdate("UPDATE Stock SET Stock = 'Tiene Stock' WHERE rowid = 4");
								System.out.println("------------------");
								
								String precio = driver.findElement(By.cssSelector("tr:nth-of-type(3) > td:nth-of-type(6)")).getText();
								
								fw.write(nombre + " " + animal + " tiene stock." + " Su precio es de " + precio + ".");
								fw.write(lineSeparator);

							} else {
								System.out.println("El producto " + animal + " no tiene stock.");
								s2.executeUpdate("UPDATE Stock SET Stock = 'No Tiene Stock' WHERE rowid = 4");
								System.out.println("------------------");

								driver.findElement(By.xpath("//a[@href='/actions/Cart.action?removeItemFromCart=&workingItemId=EST-19']"))
									.click();
								System.out.println("Se elimino del carrito.");
							}

						}
			
			            break;
			
			        }
			
			        case "Cat":{
			
			        	driver.findElement(By.cssSelector("a:nth-of-type(4) > img")).click();

						if (animal.contains("Manx")) {
							driver.findElement(By.linkText(producto)).click();

							driver.findElement(By.xpath("(//a[contains(text(),'Add to Cart')])[1]")).click();
							System.out.println("Se añadio al carrito el producto: " + nombre + " " + animal);

							String stock1 = driver.findElement(By.cssSelector("tr:nth-of-type(3) > td:nth-of-type(4)")).getText();

							if (stock1.contains("true")) {
								System.out.println("El producto tiene stock.");
								s2.executeUpdate("UPDATE Stock SET Stock = 'Tiene Stock' WHERE rowid = 3");
								System.out.println("------------------");

								String precio = driver.findElement(By.cssSelector("tr:nth-of-type(3) > td:nth-of-type(6)")).getText();
								
								fw.write(nombre + " " + animal + " tiene stock." + " Su precio es de " + precio + ".");
								fw.write(lineSeparator);

							} else {
								System.out.println("El producto " + nombre + " no tiene stock.");
								s2.executeUpdate("UPDATE Stock SET Stock = 'No Tiene Stock' WHERE rowid = 3");
								System.out.println("------------------");

								driver.findElement(By.xpath("//a[@href='/actions/Cart.action?removeItemFromCart=&workingItemId=EST-14']"))
									.click();
								System.out.println("Se elimino del carrito.");

							}

						}
			
			            break;
			
			        }
			
			        case "Fish":{
			
			        	driver.findElement(By.cssSelector("div#QuickLinks > a:nth-of-type(1) > img")).click();

						if (animal.contains("Koi")) {
							driver.findElement(By.linkText(producto)).click();

							driver.findElement(By.xpath("(//a[contains(text(),'Add to Cart')])[2]")).click();
							System.out.println("Se añadio al carrito el producto: " + nombre + " " + animal);

							String stock1 = driver.findElement(By.cssSelector("tr:nth-of-type(2) > td:nth-of-type(4)")).getText();

							if (stock1.contains("true")) {
								System.out.println("El producto tiene stock.");
								s2.executeUpdate("UPDATE Stock SET Stock = 'Tiene Stock' WHERE rowid = 1");
								System.out.println("------------------");
								
								String precio = driver.findElement(By.cssSelector("tr:nth-of-type(2) > td:nth-of-type(6)")).getText();
								
								fw.write(nombre + " " + animal + " tiene stock. Su precio es de " + precio + ".");
								fw.write(lineSeparator);
								
							} else {
								System.out.println("El producto " + nombre + " no tiene stock.");
								s2.executeUpdate("UPDATE Stock SET Stock = 'No Tiene Stock' WHERE rowid = 1");
								System.out.println("------------------");

								driver.findElement(By.cssSelector("(//a[contains(text(),'Remove')])")).click();
								System.out.println("Se elimino del carrito.");
							}

						}

								
			            break;
			
			        }
			        
			        default: {
			
			            System.out.println("Opcion incorrecta");
			
			        }
			
			 	}
				}
			} catch (SQLException e) {
				System.out.println("Error " + e.getMessage());

			}
		}
	}
}
