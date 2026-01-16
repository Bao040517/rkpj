create procedure updateproduct(
    IN p_id integer,
    IN p_name character varying,
    IN p_brand character varying,
    IN p_price numeric,
    IN p_stock integer)
    language plpgsql
as
$$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM production WHERE id = p_id) THEN
        RAISE EXCEPTION 'Product with id % does not exist', p_id;
    END IF;

    UPDATE production
    SET
        name  = p_name,
        brand = p_brand,
        price = p_price,
        stock = p_stock
    WHERE id = p_id;
    END;
$$;

alter procedure updateproduct(integer, varchar, varchar, numeric, integer) owner to postgres;

