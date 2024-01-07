create table if not exists Doner_Order (
                                          id identity,
                                          delivery_Name varchar(50) not null,
                                          delivery_Street varchar(50) not null,
                                          delivery_City varchar(50) not null,
                                          delivery_State varchar(2) not null,
                                          delivery_Zip varchar(10) not null,
                                          cc_number varchar(16) not null,
                                          cc_expiration varchar(5) not null,
                                          cc_cvv varchar(3) not null,
                                          placed_at timestamp not null
);
create table if not exists Doner (
                                    id identity, name varchar(50) not null,
                                    doner_order bigint not null,
                                    doner_order_key bigint not null,
                                    created_at timestamp not null
);
create table if not exists Ingredients_Ref (
                                              ingredients varchar(4) not null,
                                              doner bigint not null,
                                              doner_key bigint not null
);
create table if not exists Ingredients (
                                          id varchar(4) primary key ,
                                          name varchar(25) not null,
                                          type varchar(10) not null
);
alter table Doner
    add foreign key (doner_order) references Doner_Order(id);
alter table Ingredients_Ref
    add foreign key (ingredients) references Ingredients(id);