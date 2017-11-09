package yanivProduction.CouponSystemServer;

import java.util.HashSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import clientFacade.AdminFacade;
import clientFacade.ClientType;
import javaBeans.Company;
import javaBeans.Customer;
import webJavaBeans.WebCompany;
import webJavaBeans.WebCustomer;;

@Path("/admin")
public class Admin {

	private AdminFacade getFacade() {
		AdminFacade admin = new AdminFacade().login("admin", "1234", ClientType.ADMIN);
		return admin;

	}

	//#################
	//Admin-Customer API 
	//#################	

	@GET
	@Path("/customer/{id}")
	@Produces("application/json")
	public WebCustomer getCustomer(@PathParam("id") int id) {

		AdminFacade admin = getFacade();

		Customer Customer = admin.getCustomer(id);
		WebCustomer webCustomer = WebCustomer.returnWebCustomer(Customer);

		return webCustomer;
	}

	@POST
	@Path("/customer")
	@Consumes("application/json")
	public void postCustomer(WebCustomer webCustomer) {

		AdminFacade admin = getFacade();
		Customer cust = WebCustomer.returnCustomer(webCustomer);

		admin.createCustomer(cust);
	}

	@PUT
	@Path("/customer")
	@Consumes("application/json")
	public void updateCustomer(WebCustomer webCustomer) {

		AdminFacade admin = getFacade();
		Customer cust = WebCustomer.returnCustomer(webCustomer);

		admin.updateCustomer(cust);
	}

	@DELETE
	@Path("/customer")
	@Consumes("application/json")
	public void deleteCustomer(WebCustomer webCustomer) {

		AdminFacade admin = getFacade();
		Customer cust = WebCustomer.returnCustomer(webCustomer);

		admin.removeCustomer(cust);
	}

	@GET
	@Path("/customer/all")
	@Produces("application/json")
	public HashSet<WebCustomer> doGetCustomers() {

		AdminFacade admin = getFacade();

		HashSet<Customer> companies = admin.getAllCustomers();
		HashSet<WebCustomer> webCompanies = new HashSet<>();
		for (Customer customer : companies) {
			webCompanies.add(new WebCustomer(customer));
		}
		return webCompanies;
	}

	//#################
	//Admin-Company API 
	//#################	

	@GET
	@Path("/company/{id}")
	@Produces("application/json")
	public WebCompany getCompany(@PathParam("id") int id) {

		AdminFacade admin = getFacade();

		Company comp = admin.getCompany(id);
		WebCompany webComp = new WebCompany(comp);
		return webComp;
	}

	@POST
	@Path("/company")
	@Consumes("application/json")
	public void postCompany(WebCompany webCompany) {

		AdminFacade admin = getFacade();
		Company comp = WebCompany.retutnCompany(webCompany);

		admin.CreateCompany(comp);
	}

	@PUT
	@Path("/company")
	@Consumes("application/json")
	public void updateCompany(WebCompany webCompany) {

		AdminFacade admin = getFacade();
		Company comp = WebCompany.retutnCompany(webCompany);

		admin.updateCompany(comp);
	}

	@DELETE
	@Path("/company")
	@Consumes("application/json")
	public void deleteCompany(WebCompany webCompany) {

		AdminFacade admin = getFacade();
		Company comp = WebCompany.retutnCompany(webCompany);

		admin.removeCompany(comp);
	}

	@GET
	@Path("/company/all")
	@Produces("application/json")
	public HashSet<WebCompany> doGetCompanies() {

		AdminFacade admin = getFacade();

		HashSet<Company> companies = admin.getAllCompanies();
		HashSet<WebCompany> webCompanies = new HashSet<>();
		for (Company comp : companies) {
			webCompanies.add(WebCompany.retutnWebCompany(comp));
		}
		return webCompanies;
	}

}