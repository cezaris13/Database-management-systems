Select * from information_schema.key_column_usage;

with table_keys (tableName,frK,prK) as(
	Select a.table_name,count(a.position_in_unique_constraint),(count(a.ordinal_position)-count(a.position_in_unique_constraint)) 
	FROM information_schema.key_column_usage as a
	Group by a.table_schema,a.table_name)

select tableName,prK,frK 
from table_keys
where frK > 1;

-- final one 
with table_ForeignKey (tableName,foreignKey) as(
	Select a.table_name,count(a.position_in_unique_constraint) 
	FROM information_schema.key_column_usage as a
	Group by a.table_catalog,a.table_schema,a.table_name)

select tableName,foreignKey 
from table_ForeignKey
where foreignKey > 1;

Select a.table_name,count(a.position_in_unique_constraint) 
FROM information_schema.key_column_usage as a
Group by a.table_catalog,a.table_schema,a.table_name
Having count(a.position_in_unique_constraint)>1;

