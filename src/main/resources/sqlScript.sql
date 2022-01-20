--DROP TABLE IF EXISTS customers, inventory, menu, menu_inv, orders, orders_submitted, prices, inventory, roles, users;
--DROP PROCEDURE IF EXISTS remaining_inv, update_inv;

CREATE OR REPLACE PROCEDURE remaining_inv(INOUT var1 INTEGER, IN var2 INTEGER, IN var4 VARCHAR) 
AS $$
DECLARE 
	inventory_quantity INTEGER;
BEGIN 
	SELECT quantity 
	INTO inventory_quantity 
	FROM inventory WHERE ingredient_name=var4;

	var1 := inventory_quantity - (var1 * var2);
	IF var1 < 0 THEN 
		var1 := NULL;
	END IF;
END;
$$ LANGUAGE plpgsql;
END;


CREATE OR REPLACE PROCEDURE update_inv(IN var1 VARCHAR, IN var2 INTEGER)
AS $$
BEGIN 
	UPDATE inventory SET quantity=var2 WHERE ingredient_name=var1;
END;
$$ LANGUAGE plpgsql;
END;

CREATE OR REPLACE PROCEDURE update_available(IN var1 VARCHAR, IN var2 INTEGER)
AS $$
DECLARE 
	new_amount INTEGER;
BEGIN 
	SELECT available 
	INTO new_amount 
	FROM menu WHERE food_name = var1;

	new_amount := new_amount + var2;

	UPDATE menu SET available=new_amount WHERE food_name=var1;	
END;
$$ LANGUAGE plpgsql;
END; 


CREATE TABLE menu(
	food_name VARCHAR(50) PRIMARY KEY,
	description VARCHAR(100),
	price NUMERIC(4,2) NOT NULL,
	available INTEGER CHECK(available >= 0) 
--	CONSTRAINT fk_prices
--	FOREIGN KEY (price) REFERENCES prices(id)
--	ON DELETE SET NULL 
);


CREATE TABLE inventory(
	ingredient_name VARCHAR(50) PRIMARY KEY,
	quantity INTEGER NOT NULL CHECK (quantity >= 0)
);

CREATE TABLE menu_inv(
	food_name VARCHAR(50) NOT NULL,
	ingredient_name VARCHAR(50) NOT NULL,
	calls_for INTEGER CHECK (calls_for > 0),
	CONSTRAINT fk_menu
		FOREIGN KEY (food_name) 
		REFERENCES menu(food_name)
		ON DELETE CASCADE
);

ALTER TABLE menu_inv ADD CONSTRAINT fk_inv
FOREIGN KEY (ingredient_name) REFERENCES inventory(ingredient_name);


--logindao
CREATE TABLE roles(
	id SERIAL PRIMARY KEY,
	role_name VARCHAR(25) NOT NULL UNIQUE
);


CREATE TABLE users(
	id SERIAL PRIMARY KEY, 
	firstname VARCHAR(30) NOT NULL,
	lastname VARCHAR(30) NOT NULL,
	email VARCHAR(30),
	phone_number VARCHAR(30),
	dob VARCHAR(20), 
	user_id VARCHAR(20) NOT NULL UNIQUE,
	secret VARCHAR NOT NULL,
	rolez VARCHAR NOT NULL, 
	CONSTRAINT fk_roles
		FOREIGN KEY (rolez)
		REFERENCES roles(role_name)
		ON DELETE SET NULL
);


CREATE TABLE customers(
	customer_id SERIAL PRIMARY KEY,
	reward_id INTEGER NOT NULL CHECK(reward_id > 0),
	reward_points INTEGER,
	id INTEGER,
	CONSTRAINT fk_users
	FOREIGN KEY (id)
	REFERENCES users(id)
	ON DELETE CASCADE
);


CREATE TABLE orders_submitted (
	id SERIAL PRIMARY KEY,
	food_name VARCHAR(50) NOT NULL,
	quantity INTEGER CHECK (quantity > 0),
	charge NUMERIC(4,2) CHECK (charge > 0),
	customer_id INTEGER
	--CONSTRAINT fk_customer
	--	FOREIGN KEY (customer_id) 
	--	REFERENCES customers(customer_id)
	--	ON DELETE CASCADE 
);


--add Roles
INSERT INTO roles (role_name) 
VALUES ('Manager'), ('Employee'), ('Customer')





--------------------------------------------------------------------------------------------------------------------
-- Extras
--INSERT INTO users (firstname, lastname, email, phone_number, dob, user_id, secret, rolez)
--VALUES ('John', 'Doe', 'jdoe@bakery.com', '111.111.1111', '4/14','jdoe111',crypt('password', gen_salt('bf')),'Manager');

--INSERT INTO menu (food_name, description, price) 
--VALUES ('rasberry scone', 'delightful mid-day snack', 2.00);

--INSERT INTO recipe (ingredient_name, calls_for)
--VALUES ('egg',2);

--INSERT INTO menu_recipe (food_name, ingredient_name) VALUES ('rasberry scone', 'egg');

--DELETE FROM orders_submitted WHERE customer_id = 5230;




--done
--CREATE DATABASE project0;
--CALL remaining_inv (CAST('10' AS integer), CAST('4' AS integer), 'egg');


--testing things 
--SELECT secret,rolez FROM users WHERE user_id='jCoe222'; 

--SELECT * FROM users WHERE user_id = 'jdoe111' AND secret = crypt('password', secret);

--SELECT * FROM users WHERE user_id = 'nanny';

--SELECT * FROM inventory;

--INSERT INTO inventory(ingredient_name , quantity) 
--VALUES ('egg',50);
--SELECT * FROM inventory;

--SELECT quantity FROM inventory WHERE ingredient_name='egg';

--CALL test(2,4,50);
--DROP PROCEDURE test;
--CREATE PROCEDURE9 test(IN var1 INTEGER, IN var2 INTEGER, INOUT var3 INTEGER) AS $$
--BEGIN 
	--var3 := var3 - (var1 * var2);
--END 
--$$ LANGUAGE plpgsql;
--END

--CALL test2(2,4,'egg');

--CALL remaining_inv (2,4,'milk');


--INSERT INTO inventory (ingredient_name, quantity)
--VALUES ('strawberries', 20);



--CALL remaining_inv (4,4,'egg');
--UPDATE inventory SET quantity=200 WHERE ingredient_name='egg';
--SELECT * FROM inventory;

--DELETE FROM inventory WHERE ingredient_name = 'milk';


--INSERT INTO menu (food_name , description , price , available )
--VALUES ('apple pie', 'warms your heart', 4.00, 4);

--SELECT price FROM menu WHERE food_name='apple pie' AND available >= 2;


--use this...
--SELECT 
--	* 
--FROM
--	menu
--RIGHT JOIN menu_inv using(food_name);

--SELECT * FROM inventory WHERE ingredient_name='eggs';

--CREATE TABLE orders (
--	order_id SERIAL PRIMARY KEY, 
--	customer_id INTEGER NOT NULL, 
--	date_time TIMESTAMP
--);

--CALL update_available('Fruit Tart', 10); 

--INSERT INTO menu (food_name, description, price, available ) VALUES ();
   


