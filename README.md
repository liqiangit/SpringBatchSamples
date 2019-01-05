# SpringBatchSamples
spring-batch 单元测试
注意：implements ItemStream的open,update,close方法出现异常会导致job异常终止，必要的是要try catch
reader,processor,writer生命周期打印
ImportUserItemReader.open
list.size()=5
耗时0

ImportUserItemWriter.open
ImportUserItemReader.update
ImportUserItemWriter.update
0.00
2
ImportUserItemReader.update
ImportUserItemWriter.update
0.40
2
ImportUserItemReader.update
ImportUserItemWriter.update
0.80
1
ImportUserItemReader.update
ImportUserItemWriter.update
1.00
ImportUserItemReader.close
ImportUserItemWriter.close