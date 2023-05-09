package ma.fstt;

import model.CategoryDAO;
import model.ParametrageDeReferenceDAO;
import model.TypeDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManagementUI {
    private ParametrageDeReferenceDAO productDAO;
    private Scanner scanner;



    public ProductManagementUI() {
        try {
            productDAO = new ParametrageDeReferenceDAO();
            scanner = new Scanner(System.in);
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            System.exit(1);
        }
    }

    public void run() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nProduct Management Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. List All Products");
            System.out.println("5. Exit");
            System.out.println("6. Add Type");
            System.out.println("7. Add Category");


            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    listAllProducts();
                    break;
                case 5:
                    exit = true;
                    break;
                case 6:
                    addType();
                    break;
                case 7:
                    addCategory();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

        }

        scanner.close();
    }


    private void addType() {
        System.out.println("\nAdd Type");
        System.out.print("Enter type name: ");
        String typeName = scanner.nextLine();

        try {
            // Create the Type object
            Type newType = new Type(0, typeName);

            // Save the type to the database
            TypeDAO typeDAO = new TypeDAO();
            typeDAO.save(newType);

            System.out.println("Type added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add type: " + e.getMessage());
        }
    }

    private void addCategory() {
        System.out.println("\nAdd Category");
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();

        System.out.print("Enter type name for the category: ");
        String typeName = scanner.nextLine();

        try {
            // Fetch the Type object by name
            TypeDAO typeDAO = new TypeDAO();
            Type categoryType = typeDAO.getByName(typeName);

            // Create the Category object with the retrieved Type object
            Category newCategory = new Category(0, categoryName, categoryType);

            // Save the category to the database
            CategoryDAO categoryDAO = new CategoryDAO();
            categoryDAO.save(newCategory);

            System.out.println("Category added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add category: " + e.getMessage());
        }
    }



    private void addProduct() {
        System.out.println("\nAdd Product");
        System.out.print("Enter product type: ");
        String type = scanner.nextLine();
        System.out.print("Enter product category: ");
        String category = scanner.nextLine();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter product stockMax: ");
        int stockMax = scanner.nextInt();
        System.out.print("Enter product stockMin: ");
        int stockMin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter product reference: ");
        String reference = scanner.nextLine();

        System.out.print("Enter product brand: ");
        String brand = scanner.nextLine();
        // Add code to fetch other product attributes from the user

        try {
            // Fetch the Type and Category objects by name
            TypeDAO typeDAO = new TypeDAO();
            Type productType = typeDAO.getByName(type);
            CategoryDAO categoryDAO = new CategoryDAO();
            Category productCategory = categoryDAO.getByName(category);

            // Create the ParametrageDeReference object with the retrieved Type and Category objects
            ParametrageDeReference product = new ParametrageDeReference(0, productType, productCategory, quantity, stockMax, stockMin, reference, brand, 0);

            // Save the product to the database
            productDAO.save(product);

            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add product: " + e.getMessage());
        }
    }

    private void updateProduct() {
        System.out.println("\nUpdate Product");
        System.out.print("Enter product ID: ");
        long productId = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character

        try {
            // Fetch the existing product from the database
            ParametrageDeReference product = productDAO.getOne(productId);

            if (product == null) {
                System.out.println("Product not found.");
                return;
            }

            // Prompt the user to update product attributes
            System.out.print("Enter new product type (current: " + product.getType().getName() + "): ");
            String newType = scanner.nextLine();
            System.out.print("Enter new product category (current: " + product.getCategory().getName() + "): ");
            String newCategory = scanner.nextLine();
            // Add code to fetch other updated product attributes from the user

            // Fetch the Type and Category objects by name
            TypeDAO typeDAO = new TypeDAO();
            Type newProductType = typeDAO.getByName(newType);
            CategoryDAO categoryDAO = new CategoryDAO();
            Category newProductCategory = categoryDAO.getByName(newCategory);

            // Update the product attributes with the new values
            product.setType(newProductType);
            product.setCategory(newProductCategory);
            // Add code to update other product attributes

            // Update the product in the database
            productDAO.update(product);

            System.out.println("Product updated successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to update product: " + e.getMessage());
        }
    }

    private void deleteProduct() {
        System.out.println("\nDelete Product");
        System.out.print("Enter product ID: ");
        long productId = scanner.nextLong();
        scanner.nextLine(); // Consume the newline character

        try {
            // Fetch the existing product from the database
            ParametrageDeReference product = productDAO.getOne(productId);

            if (product == null) {
                System.out.println("Product not found.");
                return;
            }

            // Delete the product from the database
            productDAO.delete(product);

            System.out.println("Product deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to delete product: " + e.getMessage());
        }
    }

    private void listAllProducts() {
        System.out.println("\nAll Products:");

        try {
            // Retrieve all products from the database
            List<ParametrageDeReference> productList = productDAO.getAll();

            if (productList.isEmpty()) {
                System.out.println("No products found.");
            } else {
                for (ParametrageDeReference product : productList) {
                    System.out.println("ID: " + product.getId());
                    System.out.println("Type: " + product.getType().getName());
                    System.out.println("Category: " + product.getCategory().getName());
                    // Add code to display other product attributes
                    System.out.println("--------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve products: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ProductManagementUI ui = new ProductManagementUI();
        ui.run();
    }
}
