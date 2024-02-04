package org.study.springbatch.job;


import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SimpleJobConfiguration {
	//
	// @Bean
	// public Job yourJob(JobRepository jobRepository, Step myStep) {
	// 	return new JobBuilder("yourJob", jobRepository)
	// 		.start(myStep)
	// 		// .incrementer(new RunIdIncrementer())
	// 		.build();
	// }
	//
	// @Bean
	// @JobScope
	// public Step myStep(JobRepository jobRepository, Tasklet myTasklet, PlatformTransactionManager transactionManager) {
	// 	return new StepBuilder("myStep", jobRepository)
	// 		.tasklet(myTasklet, transactionManager)
	// 		.build();
	// }
	//
	// @Bean
	// public Tasklet myTasklet() {
	// 	return ((contribution, chunkContext) -> {
	// 		JobParameters jobParameters =  contribution.getStepExecution().getJobExecution().getJobParameters();
	// 		LocalDateTime localDateTime = jobParameters.getLocalDateTime("date");
	// 		log.info(" >>>>>>> jobParameter" + localDateTime );
	// 		log.info(">>>> This is Step");
	// 		return RepeatStatus.FINISHED;
	// 	});
	// }
	//


}