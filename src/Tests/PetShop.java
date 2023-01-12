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
import Metodos.Sql;

public class PetShop extends Metodos {
	public class PetShop1 extends Sql {

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

			String query = "SELECT * FROM Stock";
			String nombre = null;
			String animal = null;
			String producto = null;
			String dato = null;

			try {
				Connection con = DriverManager.getConnection(
						"jdbc:sqlite:C:\\Users\\HD345AP\\Downloads\\sqlite-tools-win32-x86-3400000\\sqlite-tools-win32-x86-3400000\\product.db");

				Statement s = con.createStatement();

				con.setAutoCommit(true);

				// s.execute(query);

				ResultSet rs = s.executeQuery(query);
				while (rs.next()) {

					nombre = rs.getString("Nombre");
					animal = rs.getString("Animal");
					producto = rs.getString("Producto");
					dato = nombre + " " + animal + " " + producto;
					System.out.println(dato);
					int filas = rs.getRow();
					System.out.println("El numero de filas es: " + filas);

					String archivoExcel = "List.xlsx";
					
				

					// Abro navegador
					driver.navigate().to("https://petstore.octoperf.com/actions/Catalog.action");
					int productos = retornaFilas(archivoExcel);
					File file = new File("C:\\Users\\HD345AP\\Downloads\\StockconPrecio");
					try (FileWriter fw = new FileWriter(file, true)) {
						String lineSeparator = System.getProperty("line.separator");

						fw.write("----'Productos que tienen stock'----");
						fw.write(lineSeparator);

						for (int i = 1; i <= productos; i++) {

						String nombre1 = retornaDato(i, 0, archivoExcel);
						String animal1= retornaDato(i, 1, archivoExcel);
						String producto1 = retornaDato(i, 2, archivoExcel);

						if (nombre1.contains("Dog")) {
							// Click en dogs
							driver.findElement(By.cssSelector("div#QuickLinks > a:nth-of-type(2) > img")).click();

							if (animal1.contains("Poodle")) {
								// Click en poodle
								driver.findElement(By.linkText(producto1)).click();

								// Add to cart
								driver.findElement(By.xpath("(//a[contains(text(),'Add to Cart')])[1]")).click();
								System.out.println("Se añadio al carrito el producto: " + nombre1 + " " + animal1);

								// Tiene stock
								String stock1 = driver
										.findElement(By.cssSelector("tr:nth-of-type(3) > td:nth-of-type(4)")).getText();

								if (stock1.contains("true")) {

									System.out.println("El producto tiene stock.");
									writeCellValue(archivoExcel, "Hoja1", i, 3, "Tiene Stock");
									String precio = driver
											.findElement(By.cssSelector("tr:nth-of-type(3) > td:nth-of-type(6)"))
											.getText();
									fw.write(nombre1 + " " + animal1 + " tiene stock." + " Su precio es de " + precio
											+ ".");
									fw.write(lineSeparator);
									s.execute("UPDATE Stock SET Stock = 'Tiene Stock' WHERE rowid = 2");

								} else {
									System.out.println("El producto " + animal1 + "no tiene stock.");
									writeCellValue(archivoExcel, "Hoja1", i, 3, "No Tiene Stock");
									driver.findElement(By.xpath(
											"//a[@href='/actions/Cart.action?removeItemFromCart=&workingItemId=EST-8']"))
											.click();
									System.out.println("Se elimino del carrito.");
									s.execute("UPDATE Stock SET Stock = 'No Tiene Stock' WHERE rowid = 2");
								}

							}

						} else if (nombre1.contains("Birds")) {

							driver.findElement(By.cssSelector("a:nth-of-type(5) > img")).click();
							if (animal1.contains("Finch")) {
								driver.findElement(By.linkText(producto1)).click();

								// Add to cart
								driver.findElement(By.xpath("(//a[contains(text(),'Add to Cart')])[1]")).click();
								System.out.println("Se añadio al carrito el producto: " + nombre1 + " " + animal1);

								// Tiene Stock
								String stock1 = driver
										.findElement(By.cssSelector("tr:nth-of-type(4) > td:nth-of-type(4)")).getText();

								if (stock1.contains("true")) {
									System.out.println("El producto tiene stock.");
									writeCellValue(archivoExcel, "Hoja1", i, 3, "Tiene Stock");
									String precio = driver
											.findElement(By.cssSelector("tr:nth-of-type(3) > td:nth-of-type(6)"))
											.getText();
									fw.write(animal1 + " " + animal1 + " tiene stock." + " Su precio es de " + precio
											+ ".");
									fw.write(lineSeparator);
									s.execute("UPDATE Stock SET Stock = 'Tiene Stock' WHERE rowid = 4");
								} else {
									System.out.println("El producto " + animal1 + " no tiene stock.");
									// writeCellValue(archivoExcel, "Hoja1", i, 3, "No Tiene Stock");
									driver.findElement(By.xpath(
											"//a[@href='/actions/Cart.action?removeItemFromCart=&workingItemId=EST-19']"))
											.click();
									System.out.println("Se elimino del carrito.");
									s.execute("UPDATE Stock SET Stock = 'No Tiene Stock' WHERE rowid = 4");
								}
							}
						} else if (nombre1.contains("Cat")) {

							driver.findElement(By.cssSelector("a:nth-of-type(4) > img")).click();
							if (animal1.contains("Manx")) {
								driver.findElement(By.linkText(producto1)).click();

								// Add to cart
								driver.findElement(By.xpath("(//a[contains(text(),'Add to Cart')])[1]")).click();
								System.out.println("Se añadio al carrito el producto: " + nombre1 + " " + animal1);

								// Tiene Stock
								String stock1 = driver
										.findElement(By.cssSelector("tr:nth-of-type(3) > td:nth-of-type(4)")).getText();

								if (stock1.contains("true")) {
									System.out.println("El producto tiene stock.");
									writeCellValue(archivoExcel, "Hoja1", i, 3, "Tiene Stock");
									String precio = driver
											.findElement(By.cssSelector("tr:nth-of-type(3) > td:nth-of-type(6)"))
											.getText();
									fw.write(nombre1 + " " + animal1 + " tiene stock." + " Su precio es de " + precio
											+ ".");
									fw.write(lineSeparator);
									s.execute("UPDATE Stock SET Stock = 'Tiene Stock' WHERE rowid = 3");
								} else {
									System.out.println("El producto " + nombre1 + " no tiene stock.");
									writeCellValue(archivoExcel, "Hoja1", i, 3, "No Tiene Stock");
									driver.findElement(By.xpath(
											"//a[@href='/actions/Cart.action?removeItemFromCart=&workingItemId=EST-14']"))
											.click();
									System.out.println("Se elimino del carrito.");
									s.execute("UPDATE Stock SET Stock = 'No Tiene Stock' WHERE rowid = 3");
								}
							}
						} else if (nombre1.contains("Fish")) {

							driver.findElement(By.cssSelector("div#QuickLinks > a:nth-of-type(1) > img")).click();
							if (animal1.contains("Koi")) {
								driver.findElement(By.linkText(producto1)).click();

								// Add to cart
								driver.findElement(By.xpath("(//a[contains(text(),'Add to Cart')])[2]")).click();
								System.out.println("Se añadio al carrito el producto: " + nombre1 + " " + animal1);

								// Tiene Stock
								String stock1 = driver
										.findElement(By.cssSelector("tr:nth-of-type(2) > td:nth-of-type(4)")).getText();

								if (stock1.contains("true")) {
									System.out.println("El producto tiene stock.");
									writeCellValue(archivoExcel, "Hoja1", i, 3, "Tiene Stock");
									String precio = driver
											.findElement(By.cssSelector("tr:nth-of-type(2) > td:nth-of-type(6)"))
											.getText();
									fw.write(nombre1 + " " + animal1 + " tiene stock." + " Su precio es de " + precio
											+ ".");
									fw.write(lineSeparator);
									s.execute("UPDATE Stock SET Stock = 'Tiene Stock' WHERE rowid = 1");
								} else {
									System.out.println("El producto " + nombre1 + " no tiene stock.");
									writeCellValue(archivoExcel, "Hoja1", i, 3, "No Tiene Stock");
									driver.findElement(By.cssSelector("(//a[contains(text(),'Remove')])")).click();
									System.out.println("Se elimino del carrito.");
									s.execute("UPDATE Stock SET Stock = 'No Tiene Stock' WHERE rowid = 1");
								}

							}
						}

					}
					}catch (IOException e) {

						e.printStackTrace();
					}

				}

				rs.close();
				s.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("Error " + e.getMessage());
			}

		}

	}
}



