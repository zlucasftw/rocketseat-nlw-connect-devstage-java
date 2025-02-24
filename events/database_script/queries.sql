SELECT * FROM tbl_user;
SELECT * FROM tbl_subscription;
SELECT * FROM tbl_event;

SELECT COUNT(subscription_number) AS Quantity, indication_user_id,  user_name
FROM tbl_subscription
         INNER JOIN tbl_user ON tbl_subscription.indication_user_id = tbl_user.user_id
WHERE indication_user_id IS NOT NULL AND event_id = 9
GROUP BY indication_user_id
ORDER BY Quantity DESC;

INSERT INTO tbl_user VALUES (NULL, 'John Doe', 'john@doe.com');