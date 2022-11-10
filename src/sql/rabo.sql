
SELECT UPPER(m.merchantName), t.hashedShopperReference, ROUND(AVG(riskScore), 2) as averageRiskScore, COUNT(*) as totalNumberOfTransactions
FROM transaction t
INNER JOIN merchant m ON t.merchantId = m.merchantId
GROUP BY t.hashedShopperReference, m.merchantName
HAVING AVG(riskScore) >= 100
ORDER BY m.merchantName ASC, AVG(riskScore) DESC;

SELECT u.user_name, COUNT(DECODE(SUBSTR(t.transaction_id, 0, 2), '19', t.transaction_id, NULL)) as t2019,
                    COUNT(DECODE(SUBSTR(t.transaction_id, 0, 2), '20', t.transaction_id, NULL)) as t2020,
                    COUNT(DECODE(SUBSTR(t.transaction_id, 0, 2), '21', t.transaction_id, NULL)) as t2021
FROM transactions t
INNER JOIN users u ON t.user_id = u.user_id
GROUP BY u.user_name;