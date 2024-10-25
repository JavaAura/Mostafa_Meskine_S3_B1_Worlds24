package org.esports.View;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.esports.Model.Enum.TournamentStatus;
import org.esports.Service.GameService;
import org.esports.Service.PlayerService;
import org.esports.Service.TeamService;
import org.esports.Service.TournamentService;
import org.esports.Utility.GameValidator;
import org.esports.Utility.PlayerValidator;
import org.esports.Utility.TeamValidator;
import org.esports.Utility.TournamentValidator;

public class ConsoleUI {
    private final GameService gameService;
    private final PlayerService playerService;
    private final TeamService teamService;
    private final TournamentService tournamentService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(GameService gameService, PlayerService playerService, TeamService teamService, TournamentService tournamentService) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.teamService = teamService;
        this.tournamentService = tournamentService;
    }

    public void showMenu() {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Select an option:");
                System.out.println("1. Player Management");
                System.out.println("2. Team Management");
                System.out.println("3. Tournament Management");
                System.out.println("4. Game Management");

                int choice = scanner.nextInt();
                validInput = true; // If input is valid, exit the loop

                switch (choice) {
                    case 1:
                        managePlayers();
                        break;
                    case 2:
                        manageTeams();
                        break;
                    case 3:
                        manageTournaments();
                        break;
                    case 4:
                        manageGames();
                        break;
                    default:
                        System.out.println("Invalid option.");
                        validInput = false; // If invalid option, repeat
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private void managePlayers() {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Player Management:");
                System.out.println("1. Create Player");
                System.out.println("2. Update Player");
                System.out.println("3. Delete Player");

                int choice = scanner.nextInt();
                validInput = true;

                switch (choice) {
                    case 1:
                        createPlayer();
                        break;
                    case 2:
                        updatePlayer();
                        break;
                    case 3:
                        deletePlayer();
                        break;
                    default:
                        System.out.println("Invalid option.");
                        validInput = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private void manageTeams() {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Team Management:");
                System.out.println("1. Create Team");
                System.out.println("2. Update Team");

                int choice = scanner.nextInt();
                validInput = true;

                switch (choice) {
                    case 1:
                        createTeam();
                        break;
                    case 2:
                        updateTeam();
                        break;
                    default:
                        System.out.println("Invalid option.");
                        validInput = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private void manageTournaments() {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Tournament Management:");
                System.out.println("1. Create Tournament");
                System.out.println("2. Update Tournament");

                int choice = scanner.nextInt();
                validInput = true;

                switch (choice) {
                    case 1:
                        createTournament();
                        break;
                    case 2:
                        updateTournament();
                        break;
                    default:
                        System.out.println("Invalid option.");
                        validInput = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private void manageGames() {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("Game Management:");
                System.out.println("1. Create Game");
                System.out.println("2. Update Game");

                int choice = scanner.nextInt();
                validInput = true;

                switch (choice) {
                    case 1:
                        createGame();
                        break;
                    case 2:
                        updateGame();
                        break;
                    default:
                        System.out.println("Invalid option.");
                        validInput = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private void createPlayer() {
        String name = PlayerValidator.getPlayerName(scanner);
        int age = PlayerValidator.getPlayerAge(scanner);

        if (playerService.addPlayer(name, age)) {
            System.out.println("Player created successfully.");
        } else {
            System.out.println("Player creation failed.");
        }
    }

    private void updatePlayer() {
        System.out.print("Enter the ID of the player to update: ");
        Long playerId = scanner.nextLong();
        scanner.nextLine(); // Clear buffer

        String updatedName = PlayerValidator.getPlayerName(scanner);
        int updatedAge = PlayerValidator.getPlayerAge(scanner);

        if (playerService.updatePlayer(playerId, updatedName, updatedAge)) {
            System.out.println("Player updated successfully.");
        } else {
            System.out.println("Player update failed.");
        }
    }

    private void deletePlayer() {
        System.out.print("Enter the ID of the player to delete: ");
        Long playerId = scanner.nextLong();
        scanner.nextLine(); // Clear buffer

        if (playerService.deletePlayer(playerId)) {
            System.out.println("Player deleted successfully.");
        } else {
            System.out.println("Player deletion failed. Player not found.");
        }
    }

    private void createTeam() {
        scanner.nextLine();
        String name = TeamValidator.getTeamName(scanner);
        int ranking = TeamValidator.getTeamRanking(scanner);

        if (TeamValidator.isValidTeamName(name) && TeamValidator.isValidTeamRanking(ranking)) {
            if (teamService.addTeam(name, ranking)) {
                System.out.println("Team created successfully.");
            } else {
                System.out.println("Team creation failed.");
            }
        } else {
            System.out.println("Invalid team details.");
        }
    }

    private void updateTeam() {
        System.out.print("Enter the ID of the team to update: ");
        Long teamId = scanner.nextLong();
        scanner.nextLine(); // Clear buffer

        String updatedName = TeamValidator.getTeamName(scanner);
        int updatedRanking = TeamValidator.getTeamRanking(scanner);

        if (TeamValidator.isValidTeamName(updatedName) && TeamValidator.isValidTeamRanking(updatedRanking)) {
            if (teamService.updateTeam(teamId, updatedName, updatedRanking)) {
                System.out.println("Team updated successfully.");
            } else {
                System.out.println("Team update failed.");
            }
        } else {
            System.out.println("Invalid team details.");
        }
    }

    private void createTournament() {
        String title = TournamentValidator.getTournamentTitle(scanner);
        LocalDate startDate = TournamentValidator.getTournamentStartDate(scanner);
        LocalDate endDate = TournamentValidator.getTournamentEndDate(scanner);
        int numberOfSpectators = TournamentValidator.getNumberOfSpectators(scanner);
        int estimatedDuration = TournamentValidator.getEstimatedDuration(scanner);
        int breakBetweenGames = TournamentValidator.getBreakBetweenGames(scanner);
        int ceremonyTime = TournamentValidator.getCeremonyTime(scanner);
        TournamentStatus status = TournamentValidator.getTournamentStatus(scanner);

        if (tournamentService.addTournament(title, startDate, endDate, numberOfSpectators, estimatedDuration, breakBetweenGames, ceremonyTime, status)) {
            System.out.println("Tournament created successfully.");
        } else {
            System.out.println("Tournament creation failed.");
        }
    }

    private void updateTournament() {
        System.out.print("Enter the ID of the tournament to update: ");
        Long tournamentId = scanner.nextLong();
        scanner.nextLine(); // Clear buffer

        String updatedTitle = TournamentValidator.getTournamentTitle(scanner);
        LocalDate updatedStartDate = TournamentValidator.getTournamentStartDate(scanner);
        LocalDate updatedEndDate = TournamentValidator.getTournamentEndDate(scanner);
        int updatedNumberOfSpectators = TournamentValidator.getNumberOfSpectators(scanner);
        int updatedEstimatedDuration = TournamentValidator.getEstimatedDuration(scanner);
        int updatedBreakBetweenGames = TournamentValidator.getBreakBetweenGames(scanner);
        int updatedCeremonyTime = TournamentValidator.getCeremonyTime(scanner);
        TournamentStatus updatedStatus = TournamentValidator.getTournamentStatus(scanner);

        if (tournamentService.updateTournament(tournamentId, updatedTitle, updatedStartDate, updatedEndDate, updatedNumberOfSpectators, updatedEstimatedDuration, updatedBreakBetweenGames, updatedCeremonyTime, updatedStatus)) {
            System.out.println("Tournament updated successfully.");
        } else {
            System.out.println("Tournament update failed.");
        }
    }

    private void createGame() {
        String name = GameValidator.getGameName(scanner);
        int difficulty = GameValidator.getDifficulty(scanner);
        int averageDuration = GameValidator.getAverageDuration(scanner);

        if (gameService.addGame(name, difficulty, averageDuration)) {
            System.out.println("Game created successfully.");
        } else {
            System.out.println("Game creation failed.");
        }
    }

    private void updateGame() {
        System.out.print("Enter the ID of the game to update: ");
        Long gameId = scanner.nextLong();
        scanner.nextLine(); // Clear the buffer

        String name = GameValidator.getGameName(scanner);
        int difficulty = GameValidator.getDifficulty(scanner);
        int averageDuration = GameValidator.getAverageDuration(scanner);

        if (gameService.updateGame(gameId, name, difficulty, averageDuration)) {
            System.out.println("Game updated successfully.");
        } else {
            System.out.println("Game update failed.");
        }
    }

}
