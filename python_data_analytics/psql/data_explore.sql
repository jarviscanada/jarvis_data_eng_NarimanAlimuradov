-- Nariman Alimuradov

-- Show table schema 
\d+ retail;

-- 1. Show first 10 rows
SELECT 
  * 
FROM retail 
LIMIT 10;

-- 2. Check # of records
SELECT 
  COUNT(*) 
FROM retail;

-- 3. Number of clients
SELECT 
  COUNT(DISTINCT(customer_id)) 
FROM retail;

-- 4. Invoice date range
SELECT 
  MAX(invoice_date), 
  MIN(invoice_date) 
FROM retail;

-- 5. Number of merchants
SELECT 
  COUNT(DISTINCT(stock_code)) 
FROM retail;

-- 6. Average invoice amount
SELECT 
  AVG(sum) 
FROM (
  SELECT 
    SUM(quantity*unit_price) AS sum 
  FROM retail 
  GROUP BY invoice_no 
  HAVING SUM(quantity*unit_price)>0
  ) 
AS query;

-- 7. Total revenue
SELECT 
  SUM(unit_price*quantity) 
FROM retail;

-- 8. Total revenue by yyyymm
SELECT 
  CAST(EXTRACT(YEAR FROM invoice_date)*100 + EXTRACT(MONTH FROM invoice_date) AS INTEGER) AS yyyymm, 
  SUM(quantity*unit_price) 
FROM retail 
GROUP BY yyyymm
ORDER BY yyyymm ASC;
