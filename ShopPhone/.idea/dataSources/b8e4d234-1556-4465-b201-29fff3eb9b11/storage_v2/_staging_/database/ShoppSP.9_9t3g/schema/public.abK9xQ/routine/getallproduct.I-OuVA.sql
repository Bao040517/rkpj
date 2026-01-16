DROP FUNCTION IF EXISTS getallproduct();

CREATE OR REPLACE FUNCTION getallproduct()
    RETURNS TABLE (
                      id INTEGER,
                      name VARCHAR,
                      brand VARCHAR,
                      price NUMERIC,
                      stock INTEGER
                  )
AS $$
BEGIN
    RETURN QUERY
        SELECT
            p.id,
            p.name,
            p.brand,
            p.price,
            p.stock
        FROM public.production p
        ORDER BY p.id ASC;
END;
$$ LANGUAGE plpgsql;


