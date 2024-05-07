plugins {
    id("dev.architectury.loom")
    id("architectury-plugin")
}

dependencies {
    minecraft("com.mojang:minecraft:1.20.1")
    mappings(loom.officialMojangMappings())
    modCompileOnly("com.cobblemon:mod:1.4.1+1.20.1-SNAPSHOT") {
        isTransitive = false
    }
    modImplementation("net.fabricmc:fabric-loader:0.14.21")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}