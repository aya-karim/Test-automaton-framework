package DTOs.products;

import java.util.Date;
import java.util.List;

public class Datum {
	public int id;
	public String name;
	public String type;
	public double price;
	public String upc;
	public int shipping;
	public String description;
	public String manufacturer;
	public String model;
	public String url;
	public String image;
	public Date createdAt;
	public Date updatedAt;
	public List<Category> categories;
}
