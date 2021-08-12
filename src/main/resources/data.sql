INSERT INTO `roles` (`id`, `name`)
VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_COMPANY'),
	(3, 'ROLE_JOBSEEKER');

INSERT INTO `users` (`id`, `email`, `name`, `password`, `username`, `company_id`, `job_seeker_id`)
VALUES
	(1, 'salah.khudairat@gmail.com', 'company', '$2a$10$jxtdpUz2cEQDMqPtwpl.V.cDxaLypGXO8z7ZCLd8ui3Ay1v..jojm', 'company', NULL, NULL),
	(2, 'salah.khudairat+1@gmail.com', 'jobseeker', '$2a$10$iLXCbP.gdned.YHZbJ4sFOi1T/4u0qZ7NEVT1rQhDxXkhrJBNQRsS', 'jobseeker', NULL, NULL),
	(3, 'salah.khudairat+2@gmail.com', 'admin', '$2a$10$s2YHPJgFUcGFdXHzyun0.O.7af/j6v1XndGO7lTMJOnWs0rHVP/qe', 'admin', NULL, NULL),
	(4, 'salah.khudairat+3@gmail.com', 'company2', '$2a$10$5gf7BOIeb.bD6Ej.an7u8uuX.WgAmoEUcYqGh.StwOCLznAQAzikO', 'company2', NULL, NULL);

INSERT INTO `user_roles` (`user_id`, `role_id`)
VALUES
	(3, 1),
	(1, 2),
	(4, 2),
	(2, 3);




INSERT INTO `company` (`id`, `city`, `state`, `street`, `zipcode`, `user_id`)
VALUES
	(1, 'Fairfield', 'IA', '607 W Broadway', 52556, 1),
	(2, 'Austin', 'Tx', 'Tech Ridge Blvd', 4421, 4);


INSERT INTO `job_seekers` (`id`, `bio`, `country`, `current_position`, `is_remotely`, `user_id`)
VALUES
	(1, 'First Job Seeker', 'Tanzania, United Republic of', 'Software Engineer', 0, 2);



INSERT INTO `vacancy` (`id`, `job_description`, `location`, `post_from_date`, `post_to_date`, `salary_rang_from`, `salary_rang_to`, `title`, `vacancy_status`, `company_id`)
VALUES
	(1, 'Improving the markets requires proprietary trading systems nimble enough to operate at peak efficiency in even the most extreme market conditions. Optiver engineers build those systems. It starts with a rigorous training program, during which you ll learn the ins and outs of our trading systems and the engineering principles that underpin our decision-making. Some of the challenges you ll face daily include, automatically pricing diverse sets of financial instruments and guaranteeing our systems are safe and controlled in an environment with no room for error. Of course, rapidly changing market events ensure a steady flow of new and complex challenges that will require you to constantly sharpen your skills.', 'Austin, TX', '2021-05-01', '2022-05-01', 50, 100, 'Graduate Software Engineer', 1, 1),
	(3, 'operating our autonomous vehicles on public roads to enable Argo to continue its testing, monitoring, and development of its Autonomous Vehicle System (AVS). Prior to getting behind the wheel, all AVSTSs undergo a rigorous training program which, among other things, teaches them about the underlying technolo', 'Fairfield, Iowa', '2021-05-01', '2022-05-01', 50, 150, 'Autonomous Vehicle System Test Specialist', 1, 1),
	(4, 'operating our autonomous vehicles on public roads to enable Argo to continue its testing, monitoring, and development of its Autonomous Vehicle System (AVS). Prior to getting behind the wheel, all AVSTSs undergo a rigorous training program which, among other things, teaches them about the underlying technolo', 'Fairfield, Iowa', '2021-05-01', '2022-05-01', 50, 150, 'PHP Software Developer', 1, 1),
	(5, 'operating our autonomous vehicles on public roads to enable Argo to continue its testing, monitoring, and development of its Autonomous Vehicle System (AVS). Prior to getting behind the wheel, all AVSTSs undergo a rigorous training program which, among other things, teaches them about the underlying technolo', 'Fairfield, Iowa', '2021-05-01', '2022-05-01', 50, 150, 'Delivery Specialist', 1, 1),
	(6, 'operating our autonomous vehicles on public roads to enable Argo to continue its testing, monitoring, and development of its Autonomous Vehicle System (AVS). Prior to getting behind the wheel, all AVSTSs undergo a rigorous training program which, among other things, teaches them about the underlying technolo', 'Fairfield, Iowa', '2021-05-01', '2022-05-01', 50, 150, 'Program Specialist - Fairfield', 0, 1),
	(7, 'operating our autonomous vehicles on public roads to enable Argo to continue its testing, monitoring, and development of its Autonomous Vehicle System (AVS). Prior to getting behind the wheel, all AVSTSs undergo a rigorous training program which, among other things, teaches them about the underlying technolo', 'Austin, TX', '2021-05-01', '2022-05-01', 50, 150, 'Java Software Engineer II', 0, 1),
	(8, 'operating our autonomous vehicles on public roads to enable Argo to continue its testing, monitoring, and development of its Autonomous Vehicle System (AVS). Prior to getting behind the wheel, all AVSTSs undergo a rigorous training program which, among other things, teaches them about the underlying technolo', 'Austin, TX', '2021-05-01', '2022-05-01', 50, 150, 'Java Software Engineer III', 1, 2);



INSERT INTO `vacancy_application` (`id`, `apply_date`, `category`, `company_rating`, `job_seeker_id`, `vacancy_id`)
VALUES
	(1, '2021-08-11', 0, 5, 1, 1);


INSERT INTO `skills` (`id`, `name`, `job_seeker_id`)
VALUES
	(1, 'coding', 1);


INSERT INTO `work_experiences` (`id`, `company_name`, `from_date`, `last_salary`, `position`, `to_date`, `job_seeker_id`)
VALUES
	(1, 'coding', '2020-01-09 18:00:00.000000', 200, 'position', '2022-03-01 18:00:00.000000', 1);

INSERT INTO `educations` (`id`, `degree`, `from_date`, `gpa`, `to_date`, `job_seeker_id`)
VALUES
	(1, 'Bachler in SE', '2020-01-02', 2, '2022-02-02', 1);


INSERT INTO `certificates` (`id`, `expiration_date`, `issue_date`, `issued_by`, `name`, `job_seeker_id`)
VALUES
	(1, '2022-02-01', '2020-02-01', 'PHP', 'First Certificate', 1);
