CREATE DATABASE mytwitter;
CREATE USER mytwitter WITH ENCRYPTED PASSWORD 'mytwitter';
GRANT ALL PRIVILEGES ON DATABASE mytwitter TO mytwitter;
ALTER USER mytwitter WITH SUPERUSER;
\c mytwitter

CREATE DATABASE mytwitter_test;
CREATE USER mytwitter_test WITH ENCRYPTED PASSWORD 'mytwitter_test';
GRANT ALL PRIVILEGES ON DATABASE mytwitter_test TO mytwitter_test;
ALTER USER mytwitter_test WITH SUPERUSER;
\c mytwitter_test