package org.study.springbatch.job;

import java.util.Random;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DeciderJobConfiguration {
	@Bean
	public Job decideJob(JobRepository jobRepository, JobExecutionDecider decider, Step startStep, Step evenStep, Step oddStep) {
		return new JobBuilder("decideJob", jobRepository)
			.start(startStep)
			.next(decider) // 스탭의 exitcode 대신 flowExecutionStatus로 관리
			.from(decider)
				.on("ODD")
				.to(oddStep)
			.from(decider)
				.on("EVEN")
				.to(evenStep)
			.end()
			.build();
	}


	@Bean
	public Step startStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("startStep", jobRepository)
			.tasklet((contribution, chunkContext) -> {
				log.info(">>>>> Start!");
				return RepeatStatus.FINISHED;
			}, transactionManager)
			.build();
	}

	@Bean
	public Step evenStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("evenStep", jobRepository)
			.tasklet((contribution, chunkContext) -> {
				log.info(">>>>> 짝수입니다.");
				return RepeatStatus.FINISHED;
			}, transactionManager)
			.build();
	}


	@Bean
	public Step oddStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("oddStep", jobRepository)
			.tasklet((contribution, chunkContext) -> {
				log.info(">>>>> 홀수입니다.");
				return RepeatStatus.FINISHED;
			}, transactionManager)
			.build();
	}


	@Bean
	public JobExecutionDecider decider(){
		return new OddDecider();
	}

	public static class OddDecider implements JobExecutionDecider {
		@Override
		public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution){
			Random rand = new Random();

			int randomNumber = rand.nextInt(50) + 1;
			log.info("랜덤숫자 : {}", randomNumber);


			if(randomNumber % 2 == 0) {
				// 스탭의 exitcode가 아니라 flowEwecutionStatus로 관리함.
				return new FlowExecutionStatus("EVEN");
			} else {
				return new FlowExecutionStatus("ODD");
			}
		}
	}




}
