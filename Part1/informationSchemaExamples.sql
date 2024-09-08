-- tables to which I dont have permissions

 --administrable_role_authorizations
 --applicable_roles
 --attributes
 --check_constraint_routine_usage
 --check_constraints
 --column_domain_usage
 --column_options
 --column_udt_usage
 --constraint_column_usage
 --constraint_table_usage
 --domain_udt_usage
 --foreign_data_wrapper_options
 --foreign_data_wrappers
 --foreign_server_options
 --foreign_servers
 --foreign_table_options
 --foreign_tables
 --referential_constraints
 --role_routine_grants
 --role_udt_grants
 --role_usage_grants
 --sequences
 --sql_sizing_profiles
 --table_constraints
 --transforms
 --triggered_update_columns
 --triggers
 --user_defined_types
 --user_mapping_options
 --user_mappings
 --view_column_usage
 --view_routine_usage
 --view_table_usage


/*
-- one row - catalog name
-- ex. biblio
select * 
from information_schema.information_schema_catalog_name;


-- encodings - UTF8 etc.
select * 
from information_schema.character_sets;


--every table collations - c.UTF, lt-LT-x-icu
select * 
from information_schema.collations;


-- same as collations, but  has Character_set_name which is UTF8
select * 
from information_schema.collation_character_set_applicability;

-- contains all catalogs, schemas, tables, columns with which grantee can do something(you can check which tables you can select etc.)
select *
from information_schema.column_privileges;

select Distinct(table_name)
from information_schema.column_privileges
where grantee = 'pipe7709' and table_schema = 'information_schema';

-- TODO
--select * from information_schema.columns;

-- not sure 
select * from information_schema.data_type_privileges;

-- constraints and domains having that constraints(onwed by me of granted some privilege)
select * from information_schema.domain_constraints;

-- TODO
select * from information_schema.element_types;
-- role name- me
select * from information_schema.enabled_roles;

-- key_column_usage identifies all columns in the current database that are restricted by some unique, primary key, or foreign key constraint. 
-- foreign key  - position in unique contraint
select * from information_schema.key_column_usage;

--TODO
--select * from information_schema.parameters;

--same as table_privileges but without public
select * from information_schema.role_column_grants;

-- removes public thingie
select * from information_schema.role_table_grants;

--TODO
select * from information_schema.routine_privileges;
--TODO
--select * from information_schema.routines;

-- catalog, schema,  schema owner, show to which i have access to
select * from information_schema.schemata;

--The table sql_features contains information about which formal features defined in the SQL standard are supported by PostgreSQL. 
select * from information_schema.sql_features;

--The table sql_implementation_info contains information about various aspects that are left implementation-defined by the SQL standard.
select * from information_schema.sql_implementation_info;

--The table sql_languages contains one row for each SQL language binding that is supported by PostgreSQL. 
select * from information_schema.sql_languages;

--The table sql_packages contains information about which feature packages defined in the SQL standard are supported by PostgreSQL.
select * from information_schema.sql_packages;

--The table sql_parts contains information about which of the several parts of the SQL standard are supported by PostgreSQL.
select * from information_schema.sql_parts;

--The table sql_sizing contains information about various size limits and maximum values in PostgreSQL. 
--max column name,max table name etc.
select * from information_schema.sql_sizing;

--The view table_privileges identifies all privileges granted on tables or views to a currently enabled role or by a currently enabled role. 
select * from information_schema.table_privileges;

select table_name 
from information_schema.table_privileges
where grantee = 'pipe7709' OR lower(grantee) = lower('PUBLIC');
*/
-- TODO
select * from information_schema.tables;
select * from information_schema.usage_privileges;
select * from information_schema.views;
select * from information_schema.sql_implementation_info;

select * from information_schema.domains;
select * from information_schema.columns;

select * from information_schema.element_types;
