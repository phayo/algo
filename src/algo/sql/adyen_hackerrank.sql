-- No 3
SELECT job_name, MAX(execution_id) AS last_execution_id
FROM job_executions
GROUP BY job_name;

-- No 4
WITH LastExecutions AS (
    SELECT
        job_name,
        MAX(execution_id) AS last_execution_id
    FROM job_executions
    GROUP BY job_name
),
LatestTimestamps AS (
    SELECT
        execution_id,
        MAX(timestamp) AS latest_timestamp
    FROM job_status_history
    GROUP BY execution_id
)
SELECT
    le.job_name,
    MAX(le.last_execution_id),
    MAX(lt.latest_timestamp)
FROM LastExecutions le
JOIN LatestTimestamps lt
    ON le.last_execution_id = lt.execution_id
ORDER BY le.job_name;

-- No 5
WITH RankedExecutions AS (
    SELECT
        je.job_name,
        jsh.status,
        ROW_NUMBER() OVER (PARTITION BY je.job_name ORDER BY je.execution_id DESC, jsh.timestamp DESC) AS rn
    FROM job_executions je
    JOIN job_status_history jsh ON je.execution_id = jsh.execution_id
)
SELECT job_name, status
FROM RankedExecutions
WHERE rn = 1
ORDER BY job_name;