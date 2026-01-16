create function getallproduct()
    returns TABLE(id integer, name character varying, brand character varying, price numeric, stock integer)
    language plpgsql
as
$$
BEGIN
    RETURN QUERY
        SELECT
            p.id,
            p.name,
            p.brand,
            p.price,
            p.stock
        FROM production p;

END;
$$;

alter function getallproduct() owner to postgres;

