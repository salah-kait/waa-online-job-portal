INSERT INTO roles (id,name) values (1,'ROLE_ADMIN'),(2,'ROLE_COMPANY'),(3,'ROLE_JOBSEEKER')
ON CONFLICT (id) DO UPDATE
  SET id = excluded.id,
      name = excluded.name;