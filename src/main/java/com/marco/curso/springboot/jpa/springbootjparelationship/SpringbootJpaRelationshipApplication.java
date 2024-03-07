package com.marco.curso.springboot.jpa.springbootjparelationship;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marco.curso.springboot.jpa.springbootjparelationship.entities.Address;
import com.marco.curso.springboot.jpa.springbootjparelationship.entities.Client;
import com.marco.curso.springboot.jpa.springbootjparelationship.entities.ClientDetails;
import com.marco.curso.springboot.jpa.springbootjparelationship.entities.Course;
import com.marco.curso.springboot.jpa.springbootjparelationship.entities.Invoice;
import com.marco.curso.springboot.jpa.springbootjparelationship.entities.Student;
import com.marco.curso.springboot.jpa.springbootjparelationship.repositories.ClientDetailsRepository;
import com.marco.curso.springboot.jpa.springbootjparelationship.repositories.ClientRepository;
import com.marco.curso.springboot.jpa.springbootjparelationship.repositories.CourseRepository;
import com.marco.curso.springboot.jpa.springbootjparelationship.repositories.InvoiceRepository;
import com.marco.curso.springboot.jpa.springbootjparelationship.repositories.StudentRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// this.manyToOne();
		// this.manyToOneFindByIdClient();
		// this.oneToMany();
		// this.oneToManyFindById();
		// this.removeAddress();
		// this.removeAddressFindById();
		// this.oneToManyInvoiceBidireccional();
		// this.oneToManyInvoiceBidireccionalFindById();
		// this.removeInvoiceBidireccionalFindById();
		// this.removeInvoiceBidireccional();
		// this.oneToOne();
		// this.oneToOneFindById();
		// this.oneToOneBidireccional();
		// this.oneToOneBidireccionalFindById();
		// this.manyToMany();
		// this.manyToManyFindById();
		// this.manyToManyRemoveFindById();
		// this.manyToManyRemove();
		// this.manyToManyBidireccional();
		// this.manyToManyRemoveBidireccional();
		// this.manyToManyBidireccionalFindById();
		this.manyToManyRemoveBidireccionalFindById();
	}

	@Transactional
	public void manyToManyRemoveBidireccionalFindById() {
		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);
		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Optional<Course> courseOptinal1 = courseRepository.findOneWithStudents(1L);
		Optional<Course> courseOptinal2 = courseRepository.findOneWithStudents(2L);
		Course course1 = courseOptinal1.get();
		Course course2 = courseOptinal2.get();

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		Iterable<Student> studentsDB = studentRepository.saveAll(List.of(student1, student2));

		System.out.println(studentsDB);

		Optional<Student> studentOptionalDB = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDB.isPresent()) {
			Student studentDB = studentOptionalDB.get();
			Optional<Course> courseOptionalDB = courseRepository.findOneWithStudents(1L);
			if (courseOptionalDB.isPresent()) {
				Course courseDB = courseOptionalDB.get();
				studentDB.removeCourse(courseDB);

				studentRepository.save(studentDB);
				System.out.println(studentDB);
			}
		}

	}

	@Transactional
	public void manyToManyBidireccionalFindById() {
		Optional<Student> studentOptional1 = studentRepository.findOneWithCourses(1L);
		Optional<Student> studentOptional2 = studentRepository.findOneWithCourses(2L);
		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Optional<Course> courseOptinal1 = courseRepository.findOneWithStudents(1L);
		Optional<Course> courseOptinal2 = courseRepository.findOneWithStudents(2L);
		Course course1 = courseOptinal1.get();
		Course course2 = courseOptinal2.get();

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		Iterable<Student> studentsDB = studentRepository.saveAll(List.of(student1, student2));

		System.out.println(studentsDB);
	}

	@Transactional
	public void manyToManyRemoveBidireccional() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Dora");

		Course course1 = new Course("Java Master", "Pepe");
		Course course2 = new Course("SpringBoot", "Pepe");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDB = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDB.isPresent()) {
			Student studentDB = studentOptionalDB.get();
			Optional<Course> courseOptionalDB = courseRepository.findOneWithStudents(3L);
			if (courseOptionalDB.isPresent()) {
				Course courseDB = courseOptionalDB.get();
				studentDB.removeCourse(courseDB);

				studentRepository.save(studentDB);
				System.out.println(studentDB);
			}
		}

	}

	@Transactional
	public void manyToManyBidireccional() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Dora");

		Course course1 = new Course("Java Master", "Pepe");
		Course course2 = new Course("SpringBoot", "Pepe");

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToManyRemove() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Dora");

		Course course1 = new Course("Java Master", "Pepe");
		Course course2 = new Course("SpringBoot", "Pepe");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDB = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDB.isPresent()) {
			Student studentDB = studentOptionalDB.get();
			Optional<Course> courseOptionalDB = courseRepository.findById(3L);
			if (courseOptionalDB.isPresent()) {
				Course courseDB = courseOptionalDB.get();
				studentDB.getCourses().remove(courseDB);

				studentRepository.save(studentDB);
				System.out.println(studentDB);
			}
		}

	}

	@Transactional
	public void manyToManyRemoveFindById() {
		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);
		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Optional<Course> courseOptinal1 = courseRepository.findById(1L);
		Optional<Course> courseOptinal2 = courseRepository.findById(2L);
		Course course1 = courseOptinal1.get();
		Course course2 = courseOptinal2.get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		Iterable<Student> studentsDB = studentRepository.saveAll(List.of(student1, student2));

		System.out.println(studentsDB);

		Optional<Student> studentOptionalDB = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDB.isPresent()) {
			Student studentDB = studentOptionalDB.get();
			Optional<Course> courseOptionalDB = courseRepository.findById(2L);
			if (courseOptionalDB.isPresent()) {
				Course courseDB = courseOptionalDB.get();
				studentDB.getCourses().remove(courseDB);

				studentRepository.save(studentDB);
				System.out.println(studentDB);
			}
		}
	}

	@Transactional
	public void manyToManyFindById() {
		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);
		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Optional<Course> courseOptinal1 = courseRepository.findById(1L);
		Optional<Course> courseOptinal2 = courseRepository.findById(2L);
		Course course1 = courseOptinal1.get();
		Course course2 = courseOptinal2.get();

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		Iterable<Student> studentsDB = studentRepository.saveAll(List.of(student1, student2));

		System.out.println(studentsDB);
	}

	@Transactional
	public void manyToMany() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Dora");

		Course course1 = new Course("Java Master", "Pepe");
		Course course2 = new Course("SpringBoot", "Pepe");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(List.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void oneToOneBidireccionalFindById() {

		Optional<Client> clientOptional = clientRepository.findOne(2L);
		clientOptional.ifPresent(client -> {
			ClientDetails clientDetails = new ClientDetails(true, 5000);
			client.setClientDetails(clientDetails);
			Client clientDB = clientRepository.save(client);
			System.out.println(clientDB);
		});

	}

	@Transactional
	public void oneToOneBidireccional() {

		Client client = new Client("Erba", "Pura");
		ClientDetails clientDetails = new ClientDetails(true, 5000);

		client.setClientDetails(clientDetails);
		// clientDetails.setClient(client);

		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void oneToOneFindById() {

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Optional<Client> optionalClient = clientRepository.findOne(2L);
		optionalClient.ifPresent(client -> {
			client.setClientDetails(clientDetails);
			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void oneToOne() {

		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Client client = new Client("Erba", "Pura");
		client.setClientDetails(clientDetails);
		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void removeInvoiceBidireccional() {

		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("compras de la oficina", 8000L);

		client.addInvoice(invoice1).addInvoice(invoice2);

		Client clientDB = clientRepository.save(client);

		System.out.println(clientDB);

		Optional<Client> optionalClientDB = clientRepository.findOne(3L);
		optionalClientDB.ifPresent(c -> {

			// Invoice invoice3 = new Invoice("compras de la oficina", 8000L);
			// invoice3.setId(1L);

			Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L); // Optional.of(invoice3);
			invoiceOptional.ifPresent(invoice -> {
				c.removeInvoice(invoice);
				clientRepository.save(c);
				System.out.println(c);
			});

		});

	}

	@Transactional
	public void removeInvoiceBidireccionalFindById() {

		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("compras de la oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			Client clientDB = clientRepository.save(client);

			System.out.println(clientDB);

		});

		Optional<Client> optionalClientDB = clientRepository.findOne(1L);
		optionalClientDB.ifPresent(client -> {

			Invoice invoice3 = new Invoice("compras de la oficina", 8000L);
			invoice3.setId(1L);

			Optional<Invoice> invoiceOptional = Optional.of(invoice3); // invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				client.removeInvoice(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});

		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById() {

		// Optional<Client> optionalClient = clientRepository.findById(1L);
		// Optional<Client> optionalClient = clientRepository.findOneWithAddresses(1L);
		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("compras de la oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			Client clientDB = clientRepository.save(client);

			System.out.println(clientDB);

		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccional() {

		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("compras de la oficina", 8000L);

		client.addInvoice(invoice1).addInvoice(invoice2);

		/*
		 * List<Invoice> invoices = new ArrayList<>();
		 * invoices.add(invoice1);
		 * invoices.add(invoice2);
		 * client.setInvoices(invoices);
		 * 
		 * invoice1.setClient(client);
		 * invoice2.setClient(client);
		 */

		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			clientRepository.save(client);

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println(c);
			});
		});

	}

	@Transactional
	public void removeAddress() {

		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El Vergel", 1214);
		Address address2 = new Address("Vasco de Gama", 9875);
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		Client clientDB = clientRepository.save(client);

		System.out.println(clientDB);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});

	}

	@Transactional
	public void manyToOne() {

		Client client = new Client("John", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras oficinas", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);

	}

	@Transactional
	public void manyToOneFindByIdClient() {

		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {

			Client client = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("Compras oficinas", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);

		}

	}

	@Transactional
	public void oneToManyFindById() {

		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El Vergel", 1214);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);

			client.setAddresses(addresses);

			Client clientDB = clientRepository.save(client);

			System.out.println(clientDB);
		});

	}

	@Transactional
	public void oneToMany() {

		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El Vergel", 1214);
		Address address2 = new Address("Vasco de Gama", 9875);
		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		Client clientDB = clientRepository.save(client);

		System.out.println(clientDB);

		/*
		 * Invoice invoice = new Invoice("Compras oficinas", 2000L);
		 * invoice.setClient(client);
		 * Invoice invoiceDB = invoiceRepository.save(invoice);
		 * System.out.println(invoiceDB);
		 */

	}
}
