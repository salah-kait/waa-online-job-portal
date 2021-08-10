INSERT INTO `roles` (`id`, `name`)
VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_COMPANY'),
	(3, 'ROLE_JOBSEEKER');

INSERT INTO `users` (`id`, `email`, `name`, `password`, `username`, `company_id`, `job_seeker_id`)
VALUES
	(1,'salah.khudairat@gmail.com','company','$2a$10$jxtdpUz2cEQDMqPtwpl.V.cDxaLypGXO8z7ZCLd8ui3Ay1v..jojm','company',NULL,NULL);

INSERT INTO `user_roles` (`user_id`, `role_id`)
VALUES
	(1,2);

INSERT INTO `company` (`id`, `city`, `state`, `street`, `zipcode`, `user_id`)
VALUES
	(1, 'Fairfield', 'IA', '607 W Broadway', 52556, 1);


	INSERT INTO `vacancy` (`id`, `job_description`, `location`, `post_from_date`, `post_to_date`, `salary_rang_from`, `salary_rang_to`, `title`, `vacancy_status`, `company_id`)
VALUES
	(1,'Improving the markets requires proprietary trading systems nimble enough to operate at peak efficiency in even the most extreme market conditions. Optiver engineers build those systems. It starts with a rigorous training program, during which you ll learn the ins and outs of our trading systems and the engineering principles that underpin our decision-making. Some of the challenges you ll face daily include, automatically pricing diverse sets of financial instruments and guaranteeing our systems are safe and controlled in an environment with no room for error. Of course, rapidly changing market events ensure a steady flow of new and complex challenges that will require you to constantly sharpen your skills.','Austin, TX','2021-05-01','2022-05-01',50,100,'Graduate Software Engineer',0,1),
	(2,'The Battle.net & Online Products organization is home to 300+ superpowered engineers, program managers, and designers focused on the technology that powers Blizzard Entertainments games. Whether you are playing one of our titles, chatting with friends, or just shopping online, B&OP ensures that our players are immersed in engaging, exciting, and secure experiences.','Austin, TX','2021-05-01','2022-05-01',50,150,'Software Engineer - Game Services',0,1),
	(3,'operating our autonomous vehicles on public roads to enable Argo to continue its testing, monitoring, and development of its Autonomous Vehicle System (AVS). Prior to getting behind the wheel, all AVSTSs undergo a rigorous training program which, among other things, teaches them about the underlying technolo','Fairfield, Iowa','2021-05-01','2022-05-01',50,150,'Autonomous Vehicle System Test Specialist',0,1),
	(4,'operating our autonomous vehicles on public roads to enable Argo to continue its testing, monitoring, and development of its Autonomous Vehicle System (AVS). Prior to getting behind the wheel, all AVSTSs undergo a rigorous training program which, among other things, teaches them about the underlying technolo','Fairfield, Iowa','2021-05-01','2022-05-01',50,150,'PHP Software Developer',0,1),
	(5,'operating our autonomous vehicles on public roads to enable Argo to continue its testing, monitoring, and development of its Autonomous Vehicle System (AVS). Prior to getting behind the wheel, all AVSTSs undergo a rigorous training program which, among other things, teaches them about the underlying technolo','Fairfield, Iowa','2021-05-01','2022-05-01',50,150,'Delivery Specialist',0,1),
	(6,'operating our autonomous vehicles on public roads to enable Argo to continue its testing, monitoring, and development of its Autonomous Vehicle System (AVS). Prior to getting behind the wheel, all AVSTSs undergo a rigorous training program which, among other things, teaches them about the underlying technolo','Fairfield, Iowa','2021-05-01','2022-05-01',50,150,'Program Specialist - Fairfield',0,1),
	(7,'operating our autonomous vehicles on public roads to enable Argo to continue its testing, monitoring, and development of its Autonomous Vehicle System (AVS). Prior to getting behind the wheel, all AVSTSs undergo a rigorous training program which, among other things, teaches them about the underlying technolo','Austin, TX','2021-05-01','2022-05-01',50,150,'Java Software Engineer II',0,1);
