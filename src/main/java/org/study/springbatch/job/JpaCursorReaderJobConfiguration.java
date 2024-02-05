package org.study.springbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.study.springbatch.entity.Team;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class JpaCursorReaderJobConfiguration {

	@Bean
	public Job sampleJob(JobRepository jobRepository, Step sampleStep){
		return new JobBuilder("sampleJob", jobRepository)
			.start(sampleStep)
			.incrementer(new RunIdIncrementer())
			.build();
	}

	@Bean
	public Step smapleStep(JobRepository jobRepository, JpaPagingItemReader<Team> itemReader, ItemWriter<Team> itemWriter, PlatformTransactionManager transactionManager){
		return new StepBuilder("sampleStep", jobRepository)
			.<Team, Team>chunk(100, transactionManager)
			.reader(itemReader)
			.writer(itemWriter)
			.build();
	}

	@Bean
	public JpaPagingItemReader<Team> jpaPagingItemReader(EntityManagerFactory entityManagerFactory) {
		return new JpaPagingItemReaderBuilder<Team>()
			.name("jpaPagingItemreader")
			.pageSize(100)
			.entityManagerFactory(entityManagerFactory)
			.queryString("select t from Team t")
			.build();
	}

	@Bean
	public ItemWriter<Team> itemWriter() {
		return chunk -> {};
	};
}
