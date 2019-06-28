delete from Taco_Order_Tacos;
delete from Tacos_Ingredients;
delete from Tacos;
delete from Taco_Order;
delete from ingredients;

insert into ingredients (id, name, type)
    values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into ingredients (id, name, type)
    values ('COTO', 'Corn Tortilla', 'WRAP');
insert into ingredients (id, name, type)
    values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into ingredients (id, name, type)
    values ('CARN', 'Carnitas', 'PROTEIN');
insert into ingredients (id, name, type)
    values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into ingredients (id, name, type)
    values ('LETC', 'Lettuce', 'VEGGIES');
insert into ingredients (id, name, type)
    values ('CHED', 'Cheddar', 'CHEESE');
insert into ingredients (id, name, type)
    values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into ingredients (id, name, type)
    values ('SLSA', 'Salsa', 'SAUCE');
insert into ingredients (id, name, type)
    values ('SRCR', 'Sour Cream', 'SAUCE');

insert into tacos(id, created_at, name)
values (1, CURRENT_TIMESTAMP, 'Taco Bell');
insert into tacos(id, created_at, name)
values (2, CURRENT_TIMESTAMP, 'Taco Bell 2');
insert into tacos_ingredients(taco_id, ingredients_id)
values ('1', 'FLTO');
insert into tacos_ingredients(taco_id, ingredients_id)
values ('1', 'GRBF');
insert into tacos_ingredients(taco_id, ingredients_id)
values ('2', 'CHED');
insert into tacos_ingredients(taco_id, ingredients_id)
values ('2', 'JACK');
