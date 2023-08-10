using System;
using System.Diagnostics;
using System.Threading.Tasks;

namespace AsynchronousExample
{
    class Program
    {
        static async Task Main(string[] args)
        {
            int[] numbers = GenerateRandomNumbers(1000000);

            Stopwatch stopwatch = new Stopwatch();
            stopwatch.Start();

            int totalSum = await CalculateSumAsync(numbers);

            stopwatch.Stop();

            Console.WriteLine($"Total Sum: {totalSum}");
            Console.WriteLine($"Execution Time: {stopwatch.ElapsedMilliseconds} ms");

            Console.ReadLine();
        }

        static int[] GenerateRandomNumbers(int count)
        {
            Random random = new Random();
            int[] numbers = new int[count];
            for (int i = 0; i < count; i++)
            {
                numbers[i] = random.Next(1, 101); // Generate random numbers between 1 and 100
            }
            return numbers;
        }

        static async Task<int> CalculateSumAsync(int[] array)
        {
            int chunkSize = array.Length / Environment.ProcessorCount; // Splitting the array based on the number of CPU cores

            Task<int>[] tasks = new Task<int>[Environment.ProcessorCount];

            for (int i = 0; i < Environment.ProcessorCount; i++)
            {
                int start = i * chunkSize;
                int end = (i + 1) * chunkSize;

                tasks[i] = Task.Run(() => CalculateChunkSum(array, start, end));
            }

            await Task.WhenAll(tasks);

            int totalSum = 0;
            foreach (var task in tasks)
            {
                totalSum += task.Result;
            }

            return totalSum;
        }

        static int CalculateChunkSum(int[] array, int start, int end)
        {
            int sum = 0;
            for (int i = start; i < end; i++)
            {
                sum += array[i];
            }
            return sum;
        }
    }
}
