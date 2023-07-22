package com.scaler.splitwise;

import com.scaler.splitwise.commands.CommandRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class SplitwiseApplication implements CommandLineRunner {
    private CommandRegistry commandRegistryregistry;

    @Autowired
    public SplitwiseApplication(CommandRegistry commandRegistry) {
        this.commandRegistryregistry = commandRegistry;
    }

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner in = new Scanner(System.in);

        while (true){
            String input = in.nextLine();

            if (input.equals("exit")) break;

            commandRegistryregistry.execute(input);
        }
    }
}
