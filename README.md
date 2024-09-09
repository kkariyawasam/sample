# **News Portal API**

## **Overview**

The News Portal API is a RESTful service that allows for the management of news articles. The API supports operations for creating, retrieving, and searching news articles. It also manages categories and comments associated with each news article.

## **API Endpoints**

### **News Endpoints**

#### **Retrieve All News**

* **Endpoint**: `GET /api/news`  
* **Description**: Retrieves a list of all news articles.  
* **Response**: Returns a list of `NewsDto` objects containing `id`, `title`, `content`, `categories`, and `comments`.

#### **Retrieve News by ID**

* **Endpoint**: `GET /api/news/{id}`  
* **Description**: Retrieves a specific news article by its ID.  
* **Path Variable**: `id` \- The ID of the news article.  
* **Response**: Returns a `NewsDto` object containing `id`, `title`, `content`, `categories`, and `comments`.

#### **Create News**

* **Endpoint**: `POST /api/news`  
* **Description**: Creates a new news article.  
* **Request Body**: `NewsDto` object containing `title`, `content`, and `categories`.  
* **Response**: Returns a success message upon creation.

#### **Retrieve News by Category**

* **Endpoint**: `GET /api/news/category`  
* **Description**: Retrieves a list of news articles associated with a specific category.  
* **Request Parameter**: `id` \- The ID of the category.  
* **Response**: Returns a list of `NewsDto` objects.

### **Category Endpoints**

#### **Retrieve All Categories**

* **Endpoint**: `GET /api/categories`  
* **Description**: Retrieves a list of all categories.  
* **Response**: Returns a list of `CategoryDto` objects containing `id` and `name`.

#### **Create Category**

* **Endpoint**: `POST /api/categories`  
* **Description**: save new category.  
* **Response**: Returns a success message upon creation.

### **Comment Endpoints**

#### **Update Comments**

* **Endpoint**: `PUT /api/comments`  
* **Description**: Update the news with a comment.  
* **Response**: Returns a success message upon creation.

## **Error Handling**

* **404 Not Found**: Returned when a news article or category with the specified ID is not found.

## **Data Models**

### **News**

* `id`: Long  
* `title`: String  
* `content`: String  
* `categories`: List of Long (Category IDs)  
* `comments`: List of String (Comment contents)

### **Category**

* `id`: Long  
* `name`: String

### **Comment**

* `id`: Long  
* `content`: String  
* `news`: News (Link to the associated news article)

## **How to Run**

1. Clone the repository: `git clone <repository-url>`  
2. Navigate to the project directory: `cd sample/newsportal`  
3. Build and run the application: `mvn spring-boot:run`

## **Author**

* **Kalpani** 

