INSERT INTO users(id,email,first_name,last_name,password,rolees)
VALUES (1,'admin@gmail.com','Adminbek','Adminov','$2a$12$a9V5B/J7SBjwcpBOvltOJ.MJvPqSXlShDC6JBIAq15OzvWZGY6qtG
','ADMIN'),
    (2,'user@gmail.com','Userbek','Userov','$2a$12$upKSWq9JotkDik1oJ2JfEeJqfuXSfYaKjxE8aYbdS0mAsTCEagAz2
','USER');

INSERT INTO shoe_size(id, size)
VALUES (1,'36'),(2,'37'),
        (3,'38'),(4,'39'),(5,'40'),(6,'41'),(7,'42'),(8,'43'),(9,'44'),(10,'45');

INSERT INTO clothing_size(id, size)
VALUES (1,'XXS'),(2,'XXS')
      ,(3,'S'),(4,'M'),(5,'L'),(6,'XL'),(7,'XXL');

INSERT INTO categories(id, category_name)
VALUES (1, 'Electronic'),
     (2, 'Clothes'),
      (3, 'School'),
      (4, 'House and garden'),
      (5, 'Shoe'),
      (6, 'Transport');

INSERT INTO subcategories(id, subcategory_name, category_id)
VALUES (1, 'Smartphone', 1),
       (2, 'Telephone', 1),
       (3, 'Audio', 1),
       (4, 'Photo and Camera', 1),
       (5, 'Notebooks and computers', 2),
       (6, 'Television', 1),
       (7, 'Console gaming', 1),
       (8, 'Pants', 2),
       (9, 'Shirt', 2),
       (10, 'Knitwear', 2),
       (11, 'Pen', 3),
       (12, 'Book', 3),
       (13, 'Tableware', 4),
       (14, 'Dishes', 4),
       (15, 'Cleaning equipment', 4),
       (16, 'Boots', 5),
       (17, 'Sandals', 5),
       (18, 'Bicycle', 6),
       (19, 'Scooter', 6);









