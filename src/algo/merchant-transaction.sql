
SELECT UPPER(m.merchantName), t.hashedShopperReference, ROUND(AVG(riskScore), 2) as averageRiskScore, COUNT(*) as totalNumberOfTransactions
FROM transaction t
INNER JOIN merchant m ON t.merchantId = m.merchantId
GROUP BY t.hashedShopperReference, m.merchantName
HAVING AVG(riskScore) >= 100
ORDER BY m.merchantName ASC, AVG(riskScore) DESC;