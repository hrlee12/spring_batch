package org.study.springbatch.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class StepNextConditionalJobConfiguration {

	// @Bean
	// public Job stepNextConditionalJob(JobRepository jobRepository, Step conditionalStep1, Step conditionalStep2, Step conditionalStep3) {
	// 	return new JobBuilder("stepNextConditionalJob", jobRepository)
	// 		.start(conditionalStep1)
	// 			.on("FAILED") // exit status가 FAILED 일 경우
	// 			.to(conditionalStep3) // Step3으로 이동한다.
	// 			.on("*") // step3의 결과 관계 없이
	// 			.end() // step3으로 이동하면 flow가 종료된다.
	// 		.from(conditionalStep1) // step1로부터
	// 			.on("*") // FAILED 외의 모든 경우
	// 			.to(conditionalStep2) // step2로 이동한다.
	// 			.next(conditionalStep3) // step2가 정상 종료되면 step으로 이동한다.
	// 			.on("*") // step3의 결과 관계 없이
	// 			.end() // step3으로 이동하면 flow가 종료된다.
	// 		.end() // job 종료
	// 		.build();
	// }
	//
	//
	// @Bean
	// public Step conditionalStep1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	// 	return new StepBuilder("conditionalStep1", jobRepository)
	// 		.tasklet((contribution, chunkContext) -> {
	// 			log.info(">>>>> This is stepNextConditionalJob Step1");
	//
	// 			/**
	// 			 ExitStatus를 FAILED로 지정한다.
	// 			 해당 status를 보고 flow가 진행된다.
	// 			 **/
	// 			// contribution.setExitStatus(ExitStatus.FAILED);
	//
	// 			return RepeatStatus.FINISHED;
	// 		}, transactionManager)
	// 		.build();
	// }
	//
	// @Bean
	// public Step conditionalStep2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	// 	return new StepBuilder("conditionalStep2", jobRepository)
	// 		.tasklet((contribution, chunkContext) -> {
	// 			log.info(">>>>> This is stepNextConditionalJob Step2");
	// 			return RepeatStatus.FINISHED;
	// 		}, transactionManager)
	// 		.build();
	// }
	//
	// @Bean
	// public Step conditionalStep3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	// 	return new StepBuilder("conditionalStep3", jobRepository)
	// 		.tasklet((contribution, chunkContext) -> {
	// 			log.info(">>>>> This is stepNextConditionalJob Step3");
	// 			return RepeatStatus.FINISHED;
	// 		}, transactionManager)
	// 		.build();
	// }
}

